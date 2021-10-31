package com.rolesandusers.test.service;

import com.rolesandusers.test.web.dto.UserDto;
import com.rolesandusers.test.web.request.AddUserRequest;
import com.rolesandusers.test.web.request.EditUserRequest;

import java.time.LocalDate;
import java.util.List;

public interface UserService {
    UserDto addUser(AddUserRequest addUserRequest);

    void deleteUser(String userName);

    UserDto updateUser(String username, EditUserRequest editUserRequest);

    UserDto getUserByName(String userName);

    List<UserDto> getAllUsers();

    List<UserDto> getUsersBornInPeriod(LocalDate after, LocalDate before);

}
