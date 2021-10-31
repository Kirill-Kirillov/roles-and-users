package com.rolesandusers.test.service;

import com.rolesandusers.test.model.entity.User;

import java.time.LocalDate;
import java.util.List;

public interface UserDaoService {
    User save(User user);

    void deleteUser(String userName);

    User updateUser(String username, User user);

    User getUserByName(String userName);

    List<User> getAllUsers();

    List<User> getUsersBornInPeriod(LocalDate after, LocalDate before);

}
