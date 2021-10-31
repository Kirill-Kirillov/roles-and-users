package com.rolesandusers.test.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoleDto {
    @NotBlank(message = "Поле 'name' не может быть пустым")
    private String name;

    public void setName(String name) {
        this.name = name.toUpperCase();
    }
}
