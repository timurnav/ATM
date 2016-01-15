package ru.simplewebapp.repository;

import org.springframework.stereotype.Repository;
import ru.simplewebapp.model.Account;

@Repository
public class AccountRepository {
    private static Account account = new Account(1111111111111111L, 1234, 10000, 0);

    //Returns a reference to the entity in JpaRepository
    public Account getOne(Long cardNumber) {
        if (cardNumber == account.getCardNumber()) {
            return account;
        } else {
            return null;
        }
    }

    //Returns object
    public Account findOne(Long cardNumber) {
        if (cardNumber == account.getCardNumber()) {
            return account;
        } else {
            return null;
        }
    }

    public Account save(Account account) {
        return account;
    }
}
