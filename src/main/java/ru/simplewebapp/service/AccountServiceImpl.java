package ru.simplewebapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.simplewebapp.AuthenticatedAccount;
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

@Service("accountDetailService")
public class AccountServiceImpl implements AccountService, UserDetailsService {

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
        return accountsRepository.getByNumber(number) != null;
    }

    @Transactional
    public Account withdraw(String number, Integer sum) {
        Account account = getAccountByNumber(number);
        Integer balance = account.getBalance();
        if (sum > balance) {
            throw new WrongOperationException();
        }
        account.setBalance(balance - sum);
        account.setDateTime(LocalDateTime.now());
        Operation operation = new Operation(Type.WITHDRAW, account, account.getDateTime(), sum);
        operationsRepository.save(operation);
        return accountsRepository.save(account);
    }

    @Transactional
    public Account checkAndGetAccount(String number, String pin) {
        Account account = accountsRepository.getByNumber(number);
        if (account.getAttempt() >= Account.MAX_ATTEMPTS) {
            throw new LockedAccountException();
        }

        if (pin.equals(account.getPin())) {
            account.cleanWrongAttempts();
        } else {
            account.incrementWrongAttempt();
            accountsRepository.save(account);
            throw new WrongPinException();
        }

        return account;
    }


    @Override
    public UserDetails loadUserByUsername(String number) throws UsernameNotFoundException {
        Account account = accountsRepository.getByNumber(number);
        if (account == null) {
            throw new UsernameNotFoundException("Account " + number + " is not found");
        }
        return new AuthenticatedAccount(account);
    }
}
