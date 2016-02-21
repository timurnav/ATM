package ru.simplewebapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.simplewebapp.model.User;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Integer> {

    User getByEmail(String email);

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.accounts WHERE u.id =:id")
    User getWithAcconts(@Param("id") int id);

    @Transactional
    @Modifying
    @Query("DELETE FROM User u WHERE u.id=:id")
    int delete(@Param("id") int id);

}
