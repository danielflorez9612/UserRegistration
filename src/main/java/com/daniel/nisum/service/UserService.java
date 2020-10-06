package com.daniel.nisum.service;

import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daniel.nisum.persistence.dao.UserRepository;
import com.daniel.nisum.persistence.model.Phone;
import com.daniel.nisum.persistence.model.User;
import com.daniel.nisum.web.dto.UserDto;
import com.daniel.nisum.web.error.UserAlreadyExistException;

@Service
public class UserService implements IUserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserTokenService tokenService;
	
	@Override
	public User registerUser(UserDto userDto) throws UserAlreadyExistException {
		if (emailExisting(userDto.getEmail())) {
			throw new UserAlreadyExistException();
		}
		User user = new User();
		user.setEmail(userDto.getEmail());
		user.setName(userDto.getName());
		user.setPassword(userDto.getPassword());
		user.setToken(tokenService.generateToken(userDto));
		user.setPhones(userDto.getPhones().stream().map(phoneDto-> {
			Phone phone = new Phone();
			phone.setCityCode(phoneDto.getCityCode());
			phone.setCountryCode(phoneDto.getCountryCode());
			phone.setNumber(phoneDto.getNumber());
			return phone;
		}).collect(Collectors.toList()));
		return userRepository.save(user);
	}

	private boolean emailExisting(String email) {
		return Objects.nonNull(userRepository.findByEmail(email));
	}

}
