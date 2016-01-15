package ru.simplewebapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.simplewebapp.model.Account;
import ru.simplewebapp.repository.AccountRepository;

@Service
public class CardService {

    @Autowired
    AccountRepository repository;

    public boolean checkCardNumber(long cardNumber) {
        return repository.getOne(cardNumber) != null;
    }

    public Account checkAndGetAccount(long cardNumber, int pin) {

        Account account = repository.findOne(cardNumber);

        if (pin == account.getPinCode()) {
            account.dropAttempts();
        } else {
            account.incrementAttempt();
        }

        return repository.save(account);
    }
}
