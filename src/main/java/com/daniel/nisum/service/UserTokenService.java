package com.daniel.nisum.service;

import com.daniel.nisum.web.dto.UserDto;

public interface UserTokenService {
	public String generateToken(UserDto userDto);
}
