package ru.simplewebapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.simplewebapp.model.Account;
import ru.simplewebapp.model.Operation;
import ru.simplewebapp.model.Type;
import ru.simplewebapp.repository.AccountsRepository;
import ru.simplewebapp.repository.OperationsRepository;
import ru.simplewebapp.util.exception.WrongOperationException;

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

    public boolean checkCardNumber(String number) {
        return  accountsRepository.getByNumber(number) != null;
    }

    public Account withdraw(String number, Integer sum) {
        // TODO convert from USD to CENTS

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

    public Account checkAndGetAccount(String number, String pin) {

        Account account = accountsRepository.getByNumber(number);

        if (pin.equals(account.getPin())) {
            account.dropAttempts();
        } else {
            // TODO this should be somewhere in web session, not in db...
            account.incrementAttempt();
        }

        return accountsRepository.save(account);

    }



}
