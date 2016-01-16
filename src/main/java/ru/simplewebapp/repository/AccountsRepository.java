package ru.simplewebapp.repository;

import org.springframework.data.repository.CrudRepository;
import ru.simplewebapp.model.Account;

public interface AccountsRepository extends CrudRepository<Account, Long> {
    Account getByNumber(String number);
}

