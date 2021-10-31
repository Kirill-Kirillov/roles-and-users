package com.rolesandusers.test.service.mapper;

import com.rolesandusers.test.model.entity.Role;
import com.rolesandusers.test.web.dto.RoleDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    @Mapping(target = "name", source = "name")
    RoleDto mapRoleToRoleDto(Role role);
}
