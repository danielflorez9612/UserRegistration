package com.daniel.nisum.web.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class PhoneDto {

	@NotNull
	@NotBlank
	private String number;

	@NotNull
	@NotBlank
	private String cityCode;

	@NotNull
	@NotBlank
	private String countryCode;
}
