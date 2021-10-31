package com.rolesandusers.test.web.controller;

import com.rolesandusers.test.service.UserRoleService;
import com.rolesandusers.test.web.dto.GenericResponse;
import com.rolesandusers.test.web.dto.RoleDto;
import com.rolesandusers.test.web.dto.UserDto;
import com.rolesandusers.test.web.request.SetRolesForUserRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user-role/v1")
@RequiredArgsConstructor
@Tag(name = "Manage_user's_role", description = "Управление ролями пользователей")
public class UserRoleController {

    private final UserRoleService userRoleService;

    @Operation(
            summary = "Получение ролей пользователя"
    )
    @GetMapping("/get-user-roles/{username}")
    public ResponseEntity<GenericResponse<List<RoleDto>>> getRolesByUser(@PathVariable String username) {
        List<RoleDto> rolesByUser = userRoleService.getRolesByUser(username);
        return new ResponseEntity<>(
                new GenericResponse<>(rolesByUser),
                HttpStatus.OK
        );
    }

    @PostMapping("/set-roles-for-user")
    public ResponseEntity<GenericResponse<UserDto>> setRolesForUser(
            @RequestBody SetRolesForUserRequest request) {
        return new ResponseEntity<>(
                new GenericResponse<>(userRoleService.addRolesToUser(request)),
                HttpStatus.OK);
    }

    @GetMapping("/get-used-roles")
    public ResponseEntity<GenericResponse<List<RoleDto>>> getUsedRoles() {
        return new ResponseEntity<>(
                new GenericResponse<>(userRoleService.getUsedRoles()),
                HttpStatus.OK);
    }

    @GetMapping("/get-users-by-role/{role}")
    public ResponseEntity<GenericResponse<List<UserDto>>> getUsersWhichUsedRole(@PathVariable String role) {
        return new ResponseEntity<>(
                new GenericResponse<>(userRoleService.getUsersByRole(role.toUpperCase())),
                HttpStatus.OK);
    }

}

