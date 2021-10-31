package com.rolesandusers.test.service;

import com.rolesandusers.test.web.dto.RoleDto;
import com.rolesandusers.test.web.dto.UserDto;
import com.rolesandusers.test.web.request.SetRolesForUserRequest;

import java.util.List;

public interface UserRoleService {
    UserDto addRolesToUser(SetRolesForUserRequest request);

    List<RoleDto> getRolesByUser(String username);

    List<RoleDto> getUsedRoles();

    List<UserDto> getUsersByRole(String role);
}
