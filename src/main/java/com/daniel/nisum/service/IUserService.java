package com.daniel.nisum.service;


import com.daniel.nisum.persistence.model.User;
import com.daniel.nisum.web.dto.UserDto;

@FunctionalInterface
public interface IUserService {
	User registerUser(UserDto user);
}
