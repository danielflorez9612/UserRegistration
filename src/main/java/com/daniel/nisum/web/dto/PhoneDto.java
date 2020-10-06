package com.daniel.nisum.web.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class PhoneDto {

	@NotBlank
	private String number;

	@NotBlank
	private String cityCode;

	@NotBlank
	private String countryCode;
}
