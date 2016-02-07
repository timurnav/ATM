package ru.simplewebapp.service;

import ru.simplewebapp.model.Account;

import java.util.List;

public interface AccountService {

    List<Account> getAll();

    Account getAccount(String number);

    Account getBalance(String number);

    boolean checkPresent(String number);

    Account withdraw(String number, Integer sum);

    Account checkAndGetAccount(String number, String pin);

}
