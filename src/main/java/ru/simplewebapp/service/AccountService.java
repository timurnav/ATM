package ru.simplewebapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.simplewebapp.model.Account;
import ru.simplewebapp.repository.AccountsRepository;
import ru.simplewebapp.util.exception.WrongOperationException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AccountService {

    @Autowired
    AccountsRepository repository;

    public List<Account> getAll() {
        return repository.findAll();
    }

    public Account getAccountByNumber(String number) {
        return repository.getByNumber(number);
    }

    public boolean checkCardNumber(String number) {
        return  repository.getByNumber(number) != null;
    }

    public Account withdraw(String number, Integer sum) {
        // TODO convert from USD to CENTS

        Account account = getAccountByNumber(number);
        Integer balance = account.getBalance();
        if(sum > balance) {
            throw new WrongOperationException("Not enough money on account balance to fulfuil your request please try again with different amount");
        }

        // TODO add operation to operations list
        account.setBalance(balance - sum);
        account.setDateTime(LocalDateTime.now());
        return  repository.save(account);
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
