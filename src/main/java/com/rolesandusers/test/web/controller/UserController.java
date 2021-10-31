package com.rolesandusers.test.web.controller;

import com.rolesandusers.test.service.UserService;
import com.rolesandusers.test.web.dto.GenericResponse;
import com.rolesandusers.test.web.dto.UserDto;
import com.rolesandusers.test.web.request.AddUserRequest;
import com.rolesandusers.test.web.request.EditUserRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("user/v1")
@RequiredArgsConstructor
@Tag(name = "Manage_user", description = "Управление пользователями")
public class UserController {

    private final UserService userService;

    @PostMapping()
    public ResponseEntity<GenericResponse<UserDto>> addUser(@Valid @RequestBody AddUserRequest addUserRequest) {
        UserDto savedUserDto = userService.addUser(addUserRequest);
        return new ResponseEntity<>(new GenericResponse<>(savedUserDto), HttpStatus.OK);
    }

    @PutMapping("/{userName}")
    public ResponseEntity<GenericResponse<UserDto>> editUser(
            @Valid @RequestBody EditUserRequest editUserRequest,
            @PathVariable String userName
    ){
        return new ResponseEntity<>(
                new GenericResponse<>(userService.updateUser(userName, editUserRequest)),
                HttpStatus.OK);
    }

    @DeleteMapping("/{userName}")
    public ResponseEntity<GenericResponse> deleteUser(@PathVariable String userName) {
        userService.deleteUser(userName);
        return new ResponseEntity<>(
                new GenericResponse("Успешное удаление пользователя '" + userName + "'"),
                HttpStatus.OK);
    }

    @GetMapping("/get-user-by-name/{userName}")
    public ResponseEntity<GenericResponse<UserDto>> getUserByName(@PathVariable String userName) {
        return new ResponseEntity<>(
                new GenericResponse<>(userService.getUserByName(userName)),
                HttpStatus.OK);
    }

    @GetMapping("/get-all")
    public ResponseEntity<GenericResponse<List<UserDto>>> getAllUsers() {
        List<UserDto> result = userService.getAllUsers();
        return new ResponseEntity<>(
                new GenericResponse<>(result),
                HttpStatus.OK);
    }

    @GetMapping("/get-users-born")
    public ResponseEntity<GenericResponse<List<UserDto>>> getUsersBornInPeriod(
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @RequestParam LocalDate after,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @RequestParam(required = false) LocalDate before
    ){
        List<UserDto> result = userService.getUsersBornInPeriod(after, before);
        return new ResponseEntity<>(new GenericResponse<>(result), HttpStatus.OK);
    }
}
