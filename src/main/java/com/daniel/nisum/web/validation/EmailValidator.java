package com.daniel.nisum.web.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<ValidEmail, String> {

	private Pattern pattern;
	private Matcher matcher;
	private static final String EMAIL_PATTERN = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
	private String errorMessage;

	@Override
	public void initialize(ValidEmail constraintAnnotation) {
		errorMessage = constraintAnnotation.message();
	}

	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		boolean isValid = validateEmail(email);
		if (!isValid) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(": " + errorMessage).addConstraintViolation();
		}
		return isValid;
	}

	private boolean validateEmail(String email) {
		pattern = Pattern.compile(EMAIL_PATTERN);
		matcher = pattern.matcher(email);
		return matcher.matches();
	}

}
