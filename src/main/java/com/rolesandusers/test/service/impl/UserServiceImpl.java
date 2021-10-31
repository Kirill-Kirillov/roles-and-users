package com.rolesandusers.test.service.impl;

import com.rolesandusers.test.model.entity.User;
import com.rolesandusers.test.service.UserDaoService;
import com.rolesandusers.test.service.UserService;
import com.rolesandusers.test.service.mapper.UserMapper;
import com.rolesandusers.test.web.dto.UserDto;
import com.rolesandusers.test.web.request.AddUserRequest;
import com.rolesandusers.test.web.request.EditUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDaoService userDaoService;
    private final UserMapper userMapper;

    @Override
    public UserDto addUser(AddUserRequest addUserRequest) {
        User user = userMapper.mapAddUserRequestToUser(addUserRequest);
        User savedUser = userDaoService.save(user);
        return userMapper.mapUserToUserDto(savedUser);
    }

    @Override
    public void deleteUser(String userName) {
        userDaoService.deleteUser(userName);
    }

    @Override
    public UserDto updateUser(String username, EditUserRequest editUserRequest) {
        User user = userMapper.mapEditUserRequestToUserWithoutRole(editUserRequest);
        user = userDaoService.updateUser(username, user);
        return userMapper.mapUserToUserDto(user);
    }

    @Override
    public UserDto getUserByName(String userName) {
        User userByName = userDaoService.getUserByName(userName);
        return userMapper.mapUserToUserDto(userByName);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userDaoService.getAllUsers();
        return users.stream()
                .map(user -> userMapper.mapUserToUserDto(user))
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDto> getUsersBornInPeriod(LocalDate after, LocalDate before) {
        if (before == null) {
            before = LocalDate.now();
        }
        List<User> users = userDaoService.getUsersBornInPeriod(after, before);
        return users.stream()
                .map(user -> userMapper.mapUserToUserDto(user))
                .collect(Collectors.toList());
    }

}
