package ru.simplewebapp.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.simplewebapp.model.User;

import java.util.List;

/**
 * Created by Maria on 13.02.2016.
 */
@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Integer> {
    User getByEmail(String email);

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.accounts WHERE u.id = ?1")
    User getWithAcconts(Integer id);

    @Transactional
    @Modifying
//    @Query(name = User.DELETE)
    @Query("DELETE FROM User u WHERE u.id=:id")
    int delete(@Param("id") int id);

    @Override
    @Transactional
    User save(User user);

    @Override
    User findOne(Integer id);

    @Override
    List<User> findAll();


}
