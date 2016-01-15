package ru.simplewebapp.service;

import org.springframework.stereotype.Service;
import ru.simplewebapp.model.Account;

@Service
public class CardService {

    private static Account account = new Account(1111111111111111L, 1234, 10000, 0);

    public boolean checkCardNumber(long cardNumber) {
        //mock
        return cardNumber == account.getCardNumber();
    }

    public Account getAccount(long cardNumber) {
        return account;
    }

}
