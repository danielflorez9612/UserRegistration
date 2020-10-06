package com.daniel.nisum.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.daniel.nisum.persistence.model.User;
import com.daniel.nisum.service.IUserService;
import com.daniel.nisum.web.dto.UserDto;

@RestController
public class UserController {
	
	@Autowired
	private IUserService userService;
	
	@PostMapping("/users")
	public User register(@RequestBody @Valid UserDto user) {
		return userService.registerUser(user);
	}
}
