package ru.simplewebapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.simplewebapp.model.User;
import ru.simplewebapp.repository.UserRepository;
import ru.simplewebapp.util.exception.ExceptionUtil;
import ru.simplewebapp.util.exception.NotFoundException;

import java.util.List;
import java.util.Objects;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        ExceptionUtil.check(repository.delete(id), id);
    }

    @Override
    public User get(int id) throws NotFoundException {
        return ExceptionUtil.check(repository.findOne(id), id);
    }

    @Override
    public User getByEmail(String email) throws NotFoundException {
        Objects.requireNonNull(email, "Email must not be empty");
        return ExceptionUtil.check(repository.getByEmail(email), "email=" + email);
    }

    @Override
    public List<User> getAll() {
        return repository.findAll();
    }

    @Override
    public void update(User user) {
        repository.save(user);
    }

    @Override
    @Transactional
    public void enable(int id, boolean enabled) {
        User user = get(id);
        user.setEnabled(enabled);
        repository.save(user);
    }


    @Override
    public User getWithAccounts(int id) {
     return repository.getWithAcconts(id);
    }


}
