package ru.simplewebapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.simplewebapp.model.Account;

import java.util.Optional;

public interface AccountsRepository extends JpaRepository<Account, Integer> {
    Optional<Account> getByNumber(String number);
}

