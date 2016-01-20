package ru.simplewebapp.util;

import ru.simplewebapp.model.Account;

import java.time.format.DateTimeFormatter;

public class AccountTO {

    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private final String number;
    private final Integer balance;
    private final String dateTime;

    public String getNumber() {
        return number;
    }

    public Integer getBalance() {
        return balance;
    }

    public String getDateTime() {
        return dateTime;
    }

    public AccountTO(Account account) {
        this.number = account.getNumber();
        this.balance = account.getBalance();
        this.dateTime = account.getDateTime().format(FORMATTER);
    }
}
