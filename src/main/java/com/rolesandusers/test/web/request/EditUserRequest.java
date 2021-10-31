package com.rolesandusers.test.web.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EditUserRequest {
    @Email(message = "Необходимо проверить правильность заполнения поля 'email'")
    private String email;
    private LocalDate dateOfBirth;
}
