package com.rolesandusers.test.service.impl;

import com.rolesandusers.test.model.entity.Role;
import com.rolesandusers.test.model.entity.User;
import com.rolesandusers.test.service.UserRoleDaoService;
import com.rolesandusers.test.service.UserRoleService;
import com.rolesandusers.test.service.mapper.RoleMapper;
import com.rolesandusers.test.service.mapper.UserMapper;
import com.rolesandusers.test.web.dto.RoleDto;
import com.rolesandusers.test.web.dto.UserDto;
import com.rolesandusers.test.web.request.SetRolesForUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleDaoService userRoleDaoService;
    private final RoleMapper roleMapper;
    private final UserMapper userMapper;

    @Override
    public UserDto addRolesToUser(SetRolesForUserRequest request) {
        return userMapper.mapUserToUserDto(userRoleDaoService.addRolesToUser(request));
    }

    @Override
    public List<RoleDto> getRolesByUser(String username) {
        Set<Role> rolesByUser = userRoleDaoService.getRolesByUser(username);
        return rolesByUser.stream()
                .map(role -> roleMapper.mapRoleToRoleDto(role))
                .collect(Collectors.toList());
    }

    @Override
    public List<RoleDto> getUsedRoles() {
        Set<Role> usedRoles = userRoleDaoService.getUsedRoles();
        return usedRoles.stream()
                .map(role -> roleMapper.mapRoleToRoleDto(role))
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDto> getUsersByRole(String role) {
        Set<User> userByRole = userRoleDaoService.getUsersByRole(role);
        return userByRole.stream()
                .map(user -> userMapper.mapUserToUserDto(user))
                .collect(Collectors.toList());
    }
}
