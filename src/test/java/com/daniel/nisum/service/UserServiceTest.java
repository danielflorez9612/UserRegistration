package com.daniel.nisum.service;

import com.daniel.nisum.persistence.model.User;
import com.daniel.nisum.web.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void whenUserIsSaved_AlsoSaveToken() {
        UserDto userDto = new UserDto();
        userDto.setName("Myname");
        userDto.setPassword("Password123");
        userDto.setEmail("email@valid.com");
        userDto.setPhones(new ArrayList<>());
        User user = userService.registerUser(userDto);
        assertNotNull(user.getToken());
    }
}