package com.rolesandusers.test.service.mapper;

import com.rolesandusers.test.model.entity.Role;
import com.rolesandusers.test.model.entity.User;
import com.rolesandusers.test.web.dto.UserDto;
import com.rolesandusers.test.web.request.AddUserRequest;
import com.rolesandusers.test.web.request.EditUserRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "username", source = "userName")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "dateOfBirth", source = "dateOfBirth")
    @Mapping(target = "roles", source = "roles", qualifiedByName = "getRolesForUserDto")
    UserDto mapUserToUserDto(User user);

    @Mapping(target = "userName", source = "username")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "dateOfBirth", source = "dateOfBirth")
    @Mapping(target = "roles", ignore = true)
    User mapAddUserRequestToUser(AddUserRequest request);

    @Mapping(target = "email", source = "email")
    @Mapping(target = "dateOfBirth", source = "dateOfBirth")
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "userName", ignore = true)
    User mapEditUserRequestToUserWithoutRole(EditUserRequest userDto);

    @Named("getRolesForUserDto")
    default List<String> getRolesForUserDto(Set<Role> roles) {
        return roles.stream()
                .map(role -> role.getName())
                .collect(Collectors.toList());
    }

}
