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
import ru.simplewebapp.util.exception.AtmException;

import java.time.LocalDateTime;
import java.util.List;

import static ru.simplewebapp.util.exception.AtmException.ACCOUNT_WAS_LOCKED;
import static ru.simplewebapp.util.exception.AtmException.ILLEGAL_OPERATION;
import static ru.simplewebapp.util.exception.AtmException.WRONG_PIN;

@Service("accountDetailService")
public class AccountServiceImpl implements AccountService, UserDetailsService {

    @Autowired
    AccountsRepository accountsRepository;

    @Autowired
    OperationsRepository operationsRepository;

    public List<Account> getAll() {
        return accountsRepository.findAll();
    }

    public Account getAccount(String number) {
        return accountsRepository.getByNumber(number);
    }

    @Transactional
    public Account getBalance(String number) {
        Account account = accountsRepository.getByNumber(number);
        Operation operation = new Operation(Type.REQUEST_BALANCE, account, account.getDateTime());
        operationsRepository.save(operation);
        return account;
    }

    public boolean checkPresent(String number) {
        return accountsRepository.getByNumber(number) != null;
    }

    @Transactional
    public Account withdraw(String number, Integer amount) {
        Account account = getAccount(number);
        Integer balance = account.getBalance();
        if (amount > balance) {
            throw new AtmException(ILLEGAL_OPERATION);
        }
        account.withdraw(amount);
        account.setDateTime(LocalDateTime.now());
        Operation operation = new Operation(Type.WITHDRAW, account, account.getDateTime(), amount);
        Account saved = accountsRepository.save(account);
        operationsRepository.save(operation);
        return saved;
    }

    @Transactional
    public Account checkAndGetAccount(String number, String pin) {
        Account account = accountsRepository.getByNumber(number);
        if (account.getAttempt() >= Account.MAX_ATTEMPTS) {
            throw new AtmException(ACCOUNT_WAS_LOCKED);
        }

        if (pin.equals(account.getPin())) {
            account.cleanWrongAttempts();
        } else {
            account.incrementWrongAttempt();
            accountsRepository.save(account);
            throw new AtmException(WRONG_PIN);
        }

        return account;
    }

    /**
     * This method is needed for spring security
     * @param number - card number
     * @return account for authentication
     * @throws UsernameNotFoundException
     */

    @Override
    public UserDetails loadUserByUsername(String number) throws UsernameNotFoundException {
        Account account = accountsRepository.getByNumber(number);
        if (account == null) {
            throw new UsernameNotFoundException("Account " + number + " is not found");
        }
        return new AuthenticatedAccount(account);
    }
}
