package com.rolesandusers.test.web.controller;

import com.rolesandusers.test.service.RoleService;
import com.rolesandusers.test.web.dto.GenericResponse;
import com.rolesandusers.test.web.dto.RoleDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("role/v1")
@RequiredArgsConstructor
@Tag(name = "Manage_role", description = "Управление ролями")
public class RoleController {

    private final RoleService roleService;

    @Operation (
            summary = "Добавление пользователя"
    )
    @PostMapping()
    public ResponseEntity<GenericResponse<RoleDto>> addRole(@Valid @RequestBody RoleDto roleDto) {
        return new ResponseEntity<>(
                new GenericResponse<>(roleService.addRole(roleDto)),
                HttpStatus.OK);
    }

    @DeleteMapping("/{roleName}")
    public ResponseEntity<GenericResponse> deleteRole(@PathVariable String roleName) {
        roleService.deleteRoleByName(roleName);
        return new ResponseEntity<>(
                new GenericResponse("Успешное удаление роли '" + roleName +"'"),
                HttpStatus.OK);
    }

    @GetMapping("/all-roles")
    public ResponseEntity<GenericResponse<List<RoleDto>>> getAllRoles() {
        return new ResponseEntity<>(
                new GenericResponse<>(roleService.getAllRoles()),
                HttpStatus.OK);
    }
}
