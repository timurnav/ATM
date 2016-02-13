package ru.simplewebapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.simplewebapp.model.Account;

import java.util.List;
import java.util.Optional;

public interface AccountsRepository extends JpaRepository<Account, Integer> {
    Optional<Account> getByNumber(String number);

    @Override
    List<Account> findAll();

    @Override
    Account getOne(Integer integer);

    @Override
    void delete(Integer integer);

    @Override
    @Transactional
    Account save(Account entity);
}

