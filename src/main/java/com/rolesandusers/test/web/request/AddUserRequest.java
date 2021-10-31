package com.rolesandusers.test.web.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddUserRequest {
    @NotBlank(message = "Поле 'username' не может быть пустым")
    private String username;
    @Email(message = "Необходимо проверить правильность заполнения поля 'email'")
    private String email;
    private LocalDate dateOfBirth;
}
