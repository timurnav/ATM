package ru.simplewebapp.service;

import ru.simplewebapp.model.Account;

import java.util.List;

public interface AccountService {

    List<Account> getAll();

    Account getAccountByNumber(String number);

    Account getBalanceByNumber(String number);

    boolean checkCardNumber(String number);

    Account withdraw(String number, Integer sum);

    Account checkAndGetAccount(String number, String pin);

}
