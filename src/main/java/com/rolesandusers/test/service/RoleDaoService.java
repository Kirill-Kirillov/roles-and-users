package com.rolesandusers.test.service;

import com.rolesandusers.test.model.entity.Role;

import java.util.List;

public interface RoleDaoService {
    Role save(Role role);

    void deleteByName(String roleName);

    List<Role> getAllRoles();
}
