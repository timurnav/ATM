package ru.simplewebapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.simplewebapp.model.Operation;
import ru.simplewebapp.repository.OperationsRepository;

import java.util.List;

@Service
public class OperationService {

    @Autowired
    OperationsRepository repository;

    public List<Operation> getAll() {
        return repository.findAll();
    }



}
