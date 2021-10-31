package com.rolesandusers.test.service.impl;

import com.rolesandusers.test.model.entity.Role;
import com.rolesandusers.test.service.RoleDaoService;
import com.rolesandusers.test.service.RoleService;
import com.rolesandusers.test.web.dto.RoleDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoleServiceImpl implements RoleService {

    private final RoleDaoService roleDaoService;

    @Override
    public RoleDto addRole(RoleDto roleDto) {
        Role savedRole = roleDaoService.save(
                this.mapRoleDtoToRole(roleDto));
        RoleDto savedRoleDto = this.mapRoleToRoleDto(savedRole);
        return savedRoleDto;
    }

    @Override
    public void deleteRoleByName(String roleName) {
        roleDaoService.deleteByName(roleName.toUpperCase());
    }

    @Override
    public List<RoleDto> getAllRoles() {
        List<Role> roles = roleDaoService.getAllRoles();
        return roles.stream()
                .map(role -> this.mapRoleToRoleDto(role))
                .collect(Collectors.toList());
    }

    private RoleDto mapRoleToRoleDto(Role role) {
        return new RoleDto(role.getName());
    }

    private Role mapRoleDtoToRole(RoleDto roleDto) {
        Role role = new Role();
        role.setName(roleDto.getName());
        return role;
    }
}
