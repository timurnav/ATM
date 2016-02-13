package ru.simplewebapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.simplewebapp.model.Operation;

import java.util.List;

public interface OperationsRepository extends JpaRepository<Operation, Integer> {
    @Override
    List<Operation> findAll();

    @Override
    Operation getOne(Integer integer);

    @Override
    void delete(Integer integer);

    @Override
    @Transactional
    Operation save(Operation entity);
}
