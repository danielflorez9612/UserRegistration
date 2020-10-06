package com.daniel.nisum.service;

import com.daniel.nisum.web.dto.UserDto;

@FunctionalInterface
public interface UserTokenService {
	String generateToken(UserDto userDto);
}
