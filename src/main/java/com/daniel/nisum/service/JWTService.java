package com.daniel.nisum.service;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.daniel.nisum.web.dto.UserDto;

@Service
public class JWTService implements UserTokenService {

	@Override
	public String generateToken(UserDto userDto) {
		Algorithm algorithmHS = Algorithm.HMAC256("secret");
		return JWT.create()
                .withSubject(userDto.getEmail())
		        .withIssuer("daniel@nisum")
		        .sign(algorithmHS);
	}

}
