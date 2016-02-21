package ru.simplewebapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.simplewebapp.model.Account;

import java.util.Optional;

@Repository
public interface AccountsRepository extends JpaRepository<Account, Integer> {
    Optional<Account> getByNumber(String number);
}

