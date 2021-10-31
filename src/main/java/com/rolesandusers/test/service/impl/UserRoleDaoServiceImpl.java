package com.rolesandusers.test.service.impl;

import com.rolesandusers.test.exception.RoleNotFoundException;
import com.rolesandusers.test.exception.UserNotFoundException;
import com.rolesandusers.test.model.entity.Role;
import com.rolesandusers.test.model.entity.User;
import com.rolesandusers.test.model.repository.RoleRepository;
import com.rolesandusers.test.model.repository.UserRepository;
import com.rolesandusers.test.service.UserRoleDaoService;
import com.rolesandusers.test.web.request.SetRolesForUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserRoleDaoServiceImpl implements UserRoleDaoService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    @Transactional
    public User addRolesToUser(SetRolesForUserRequest request) {
        User user = getUserByNameOrThrowIfNotExist(request.getUsername());
        Set<Role> requestRolesFromDB =  request.getRoles().stream()
                .map(role -> getRoleByNameAndThrowIfNotExist(role.getName()))
                .collect(Collectors.toSet());
        user.getRoles().addAll(requestRolesFromDB);
        user = userRepository.save(user);
        return user;
    }

    @Override
    public Set<Role> getRolesByUser(String username) {
        User user = getUserByNameOrThrowIfNotExist(username);
        return user.getRoles();
    }

    @Override
    public Set<Role> getUsedRoles() {
        Set<Role> roles = roleRepository.getRolesByUsersNotNull();
        return roles;
    }

    @Override
    public Set<User> getUsersByRole(String role) {
        Set<User> users = userRepository.findUserByRoles_nameIsAndDeletedIsFalse(role);
        return users;
    }

    private User getUserByNameOrThrowIfNotExist(String username) {
        return userRepository.findByUserNameAndDeletedIsFalse(username)
                .orElseThrow(() -> new UserNotFoundException("Пользователь '" + username + "' не найден"));
    }

    private Role getRoleByNameAndThrowIfNotExist(String name){
        return roleRepository.findByName(name)
                .orElseThrow(() -> new RoleNotFoundException("Роль с именем '" + name + "' не найдена"));
    }
}
