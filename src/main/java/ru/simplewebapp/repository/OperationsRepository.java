package ru.simplewebapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
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
    Operation save(Operation entity);
}
