package ru.simplewebapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.simplewebapp.model.Account;
import ru.simplewebapp.model.Operation;
import ru.simplewebapp.model.Type;
import ru.simplewebapp.repository.AccountsRepository;
import ru.simplewebapp.repository.OperationsRepository;
import ru.simplewebapp.util.exception.LockedAccountException;
import ru.simplewebapp.util.exception.WrongOperationException;
import ru.simplewebapp.util.exception.WrongPinException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AccountService {

    @Autowired
    AccountsRepository accountsRepository;

    @Autowired
    OperationsRepository operationsRepository;

    public List<Account> getAll() {
        return accountsRepository.findAll();
    }

    public Account getAccountByNumber(String number) {
        return accountsRepository.getByNumber(number);
    }

    @Transactional
    public Account getBalanceByNumber(String number) {
        Account account = accountsRepository.getByNumber(number);
        Operation operation = new Operation(Type.REQUEST_BALANCE, account, account.getDateTime());
        operationsRepository.save(operation);
        return account;
    }

    public boolean checkCardNumber(String number) {
        return  accountsRepository.getByNumber(number) != null;
    }

    @Transactional
    public Account withdraw(String number, Integer sum) {
        Account account = getAccountByNumber(number);
        Integer balance = account.getBalance();
        if(sum > balance) {
            throw new WrongOperationException("Not enough money on account balance to fulfuil your request please try again with different amount");
        }
        account.setBalance(balance - sum);
        account.setDateTime(LocalDateTime.now());
        Operation operation = new Operation(Type.WITHDRAW, account, account.getDateTime(), sum);
        operationsRepository.save(operation);
        return  accountsRepository.save(account);
    }

    @Transactional
    public Account checkAndGetAccount(String number, String pin) {
        Account account = accountsRepository.getByNumber(number);
        if(account.getAttempt() >= Account.MAX_ATTEMPTS) {
            throw new LockedAccountException("Account was locked");
        }

        if (pin.equals(account.getPin())) {
            account.cleanWrongAttempts();
        } else {
            account.incrementWrongAttempt();
            accountsRepository.save(account);
            throw new WrongPinException("Pin is not correct");
        }

        return account;
    }



}
