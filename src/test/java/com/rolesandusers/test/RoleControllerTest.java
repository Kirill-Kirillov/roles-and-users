package com.rolesandusers.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rolesandusers.test.exception.RoleAlreadyExistException;
import com.rolesandusers.test.service.RoleService;
import com.rolesandusers.test.web.dto.RoleDto;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAutoConfiguration
@AutoConfigureMockMvc
public class RoleControllerTest {

    @MockBean
    RoleService roleService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void sendValidRequestToCreateRole () throws Exception {

        RoleDto roleDto = new RoleDto("user");
        when(roleService.addRole(any(RoleDto.class))).thenReturn(roleDto);
        this.mockMvc.perform(post("/role/v1")
                        .content(new ObjectMapper().writeValueAsString(roleDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.responseData.name").value("user"))
                .andExpect(status().isOk());
    }

    @Test
    public void sendNotValidRequestToCreateRole () throws Exception {

        RoleDto roleDto = new RoleDto("user");
        when(roleService.addRole(any(RoleDto.class))).thenThrow(new RoleAlreadyExistException("Роль 'USER' уже существует"));
        this.mockMvc.perform(post("/role/v1")
                        .content(new ObjectMapper().writeValueAsString(roleDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("errorMessage").value("Роль 'USER' уже существует"))
                .andExpect(status().is4xxClientError());
    }
}
