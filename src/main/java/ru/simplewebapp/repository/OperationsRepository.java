package ru.simplewebapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.simplewebapp.model.Operation;

@Repository
public interface OperationsRepository extends JpaRepository<Operation, Integer> {

}
