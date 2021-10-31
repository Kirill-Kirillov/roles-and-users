package com.rolesandusers.test.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDto {
    @Schema(description = "username пользователя")
    @NotBlank(message = "Поле 'username' не может быть пустым")
    private String username;
    @Schema(description = "email пользователя")
    @Email (message = "Необходимо проверить правильность заполнения поля 'email'")
    private String email;
    @Schema(description = "День рождения пользователя")
    private LocalDate dateOfBirth;
    private List<String> roles;
}
