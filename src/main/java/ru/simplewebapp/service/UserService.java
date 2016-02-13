package ru.simplewebapp.service;

import ru.simplewebapp.model.User;
import ru.simplewebapp.util.exception.NotFoundException;

import java.util.List;

/**
 * Created by Maria on 13.02.2016.
 */
public interface UserService {
    User save(User user);

    void delete(int id) throws NotFoundException;

    User get(int id) throws NotFoundException;

    User getByEmail(String email) throws NotFoundException;

     List<User> getAll();

    void update(User user);


    void enable(int id, boolean enable);

    User getWithAccounts(int id);
}
