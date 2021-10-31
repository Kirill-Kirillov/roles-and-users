package com.rolesandusers.test.service;

import com.rolesandusers.test.web.dto.RoleDto;

import java.util.List;

public interface RoleService {
    RoleDto addRole(RoleDto roleDto);

    void deleteRoleByName(String roleName);

    List<RoleDto> getAllRoles();
}
