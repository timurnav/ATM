package ru.simplewebapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.simplewebapp.model.Operation;

public interface OperationsRepository extends JpaRepository<Operation, Integer> {
}
