package com.daniel.nisum.web.controller;

import com.daniel.nisum.web.dto.PhoneDto;
import com.daniel.nisum.web.dto.UserDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void contextLoads() throws Exception {
        assertThat(mockMvc, CoreMatchers.notNullValue());
    }

    private String toJson(UserDto userDto) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(userDto);
    }

    @Test
    public void whenAlreadyExistingEmail_ShowError() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setName("Myname");
        userDto.setPassword("Password123");
        userDto.setEmail("email@valid.com");
        userDto.setPhones(new ArrayList<>());
        mockMvc.perform(
                post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(toJson(userDto))
        );
        mockMvc.perform(
                post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(toJson(userDto))
        ).andExpect(status().isConflict())
                .andExpect(jsonPath("$.mensaje", notNullValue()));
    }

    @Test
    public void whenRegisteringUser_AlsoRegisterPhones() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setName("Myname");
        userDto.setPassword("Password123");
        userDto.setEmail("email@valid.com");
        PhoneDto phoneDto = new PhoneDto();
        phoneDto.setNumber("1234567");
        phoneDto.setCityCode("1");
        phoneDto.setCountryCode("57");
        userDto.setPhones(Collections.singletonList(phoneDto));
        mockMvc.perform(
                post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(toJson(userDto))
        ).andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.name", is("Myname")))
                .andExpect(jsonPath("$.password", is("Password123")))
                .andExpect(jsonPath("$.email", is("email@valid.com")))
                .andExpect(jsonPath("$.phones[0]", notNullValue()))
                .andExpect(jsonPath("$.phones[0].id", notNullValue()))
                .andExpect(jsonPath("$.phones[0].number", is("1234567")))
                .andExpect(jsonPath("$.phones[0].cityCode", is("1")))
                .andExpect(jsonPath("$.phones[0].countryCode", is("57")))
        ;

    }

    @Test
    public void whenRegisteringUser_ValidateEmail() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setName("Myname");
        userDto.setPassword("Password123");
        userDto.setEmail("email@valid");
        PhoneDto phoneDto = new PhoneDto();
        phoneDto.setNumber("1234567");
        phoneDto.setCityCode("1");
        phoneDto.setCountryCode("57");
        userDto.setPhones(Collections.singletonList(phoneDto));
        mockMvc.perform(
                post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(toJson(userDto))
        ).andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.mensaje", notNullValue()));
    }

    @Test
    public void whenRegisteringUser_ValidatePassword() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setName("Myname");
        userDto.setPassword("P");
        userDto.setEmail("email@valid.com");
        PhoneDto phoneDto = new PhoneDto();
        phoneDto.setNumber("1234567");
        phoneDto.setCityCode("1");
        phoneDto.setCountryCode("57");
        userDto.setPhones(Collections.singletonList(phoneDto));
        mockMvc.perform(
                post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(toJson(userDto))
        ).andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.mensaje", notNullValue()));

    }

}