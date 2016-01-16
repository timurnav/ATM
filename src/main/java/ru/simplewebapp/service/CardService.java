package ru.simplewebapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.simplewebapp.model.Account;
import ru.simplewebapp.repository.AccountsRepository;

import java.util.List;

@Service
public class CardService {

    @Autowired(required=true)
    AccountsRepository repository;

    public List<Account> getAll() {
        return repository.findAll();
    }

    public Account getOneByNumber(String number) {
        return repository.getByNumber(number);
    }

    public boolean checkCardNumber(String number) {
        return  repository.getByNumber(number) != null;
    }

    public Account checkAndGetAccount(String number, String pin) {

        Account account = repository.getByNumber(number);

        if (pin.equals(account.getPin())) {
            account.dropAttempts();
        } else {
            // TODO this should be somewhere in web session, not in db...
            account.incrementAttempt();
        }

        return repository.save(account);

    }



}
