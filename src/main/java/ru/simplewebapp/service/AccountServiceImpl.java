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
import ru.simplewebapp.util.exception.NotFoundException;
import ru.simplewebapp.util.exception.NotEnoughMoneyException;

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

    public Account getAccount(String number) {
        Account account = accountsRepository
                .getByNumber(number)
                .orElseThrow(NotFoundException::new);
        return account.ifHasAttempts();
    }

    @Transactional
    public void resetFailAttempts(String number) {
        Account account = getAccount(number);
        account.resetFailAttempts();
        accountsRepository.save(account);
    }

    @Transactional
    public int incrementFailAttempt(String number) {
        Account account = getAccount(number);
        int attempts = account.incrementFailAttempt();
        accountsRepository.save(account);
        return attempts;
    }

    @Transactional
    public Account withdraw(String number, Integer amount) {
        Account account = getAccount(number);
        Integer balance = account.getBalance();
        if (amount > balance) {
            throw new NotEnoughMoneyException();
        }
        account.withdraw(amount);
        account.setDateTime(LocalDateTime.now());
        Operation operation = new Operation(Type.WITHDRAW, account, account.getDateTime(), amount);
        Account saved = accountsRepository.save(account);
        operationsRepository.save(operation);
        return saved;
    }

    @Transactional
    public Account getBalance(String number) {
        Account account = getAccount(number);
        Operation operation = new Operation(Type.REQUEST_BALANCE, account, account.getDateTime());
        operationsRepository.save(operation);
        return account;
    }

    public void checkPresent(String number) {
        getAccount(number);
    }

    /**
     * This method is needed for spring security
     * @param number - card number
     * @return account for authentication
     * @throws UsernameNotFoundException
     */

    @Override
    public UserDetails loadUserByUsername(String number) throws UsernameNotFoundException {
        Account account = accountsRepository
                .getByNumber(number)
                .orElseThrow(() -> new UsernameNotFoundException("Account " + number + " is not found"));
        return new AuthenticatedAccount(account);
    }
}
