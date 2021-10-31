package com.rolesandusers.test.web.request;

import com.rolesandusers.test.web.dto.RoleDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SetRolesForUserRequest {
    @NotBlank
    private String username;
    @NotBlank
    private Set<RoleDto> roles = new HashSet<>();
}
