package com.daniel.nisum.web.controller;

import javax.validation.Valid;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.daniel.nisum.persistence.model.User;
import com.daniel.nisum.service.IUserService;
import com.daniel.nisum.web.dto.UserDto;

@RestController
public class UserController {

	@Setter(onMethod_={@Autowired})
	private IUserService userService;
	
	@PostMapping("/users")
	@ResponseStatus(code = HttpStatus.CREATED)
	public User register(@RequestBody @Valid UserDto user) {
		return userService.registerUser(user);
	}
}
