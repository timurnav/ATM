package ru.simplewebapp;

import ru.simplewebapp.model.Account;

public class AuthenticatedAccount {

    private Integer id;
    private String number;
    private String pin;

    public AuthenticatedAccount(Account account) {
        this.id = account.getId();
        this.number = account.getNumber();
        this.pin = account.getPin();
    }

    public AuthenticatedAccount() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
}
