package ru.simplewebapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.simplewebapp.model.Account;

public interface AccountsRepository extends JpaRepository<Account, Integer> {
    Account getByNumber(String number);
}

