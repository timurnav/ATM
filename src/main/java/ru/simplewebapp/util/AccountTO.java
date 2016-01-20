package ru.simplewebapp.util;

import ru.simplewebapp.model.Account;

public class AccountTO {

    private final String number;
    private final String balance;
    private final String dateTime;

    public String getNumber() {
        return number;
    }

    public String getBalance() {
        return balance;
    }

    public String getDateTime() {
        return dateTime;
    }

    public AccountTO(Account account) {
        this.number = account.getNumber();
        this.balance = account.getBalance().toString();
        this.dateTime = account.getDateTime().toString();
    }
}
