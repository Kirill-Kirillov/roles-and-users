package com.rolesandusers.test.model.repository;

import com.rolesandusers.test.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUserName(String userName);

    Optional<User> findByUserNameAndDeletedIsFalse(String username);

    Optional<User> findByEmail(String email);

    List<User> findUserByDateOfBirthAfterAndDateOfBirthBefore(LocalDate after, LocalDate before);

    List<User> findUserByDateOfBirthAfterAndDateOfBirthBeforeAndDeletedIsFalse(LocalDate after, LocalDate before);

    List<User> findUserByDeletedIsFalse();

    Set<User> findUserByRoles_nameIs(String role);

    Set<User> findUserByRoles_nameIsAndDeletedIsFalse(String role);

}
