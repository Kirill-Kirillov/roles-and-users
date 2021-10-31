package com.rolesandusers.test.service;

import com.rolesandusers.test.model.entity.Role;
import com.rolesandusers.test.model.entity.User;
import com.rolesandusers.test.web.request.SetRolesForUserRequest;

import java.util.Set;

public interface UserRoleDaoService {
    User addRolesToUser(SetRolesForUserRequest request);

    Set<Role> getRolesByUser(String username);

    Set<Role> getUsedRoles();

    Set<User> getUsersByRole(String role);
}
