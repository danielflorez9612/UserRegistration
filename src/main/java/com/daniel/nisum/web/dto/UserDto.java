package com.daniel.nisum.web.dto;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.daniel.nisum.web.validation.ValidEmail;
import com.daniel.nisum.web.validation.ValidPassword;

import lombok.Data;

@Data
public class UserDto {

	@NotNull
	@NotBlank
	private String name;

	@NotNull
	@NotBlank
	@ValidEmail
	private String email;
	
	@NotNull
	@NotBlank
	@ValidPassword
	private String password;
	
	@Valid
	private List<PhoneDto> phones;
}
