package com.daniel.nisum.web.dto;

import com.daniel.nisum.web.validation.ValidEmail;
import com.daniel.nisum.web.validation.ValidPassword;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class UserDto {

	@NotBlank(message = "no puede ser nulo o vacio")
	private String name;

	@NotBlank()
	@ValidEmail
	private String email;
	
	@NotBlank
	@ValidPassword
	private String password;

	@NotNull
	@Valid
	private List<PhoneDto> phones;
}
