package com.daniel.nisum.web.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<ValidPassword, String>{

	private Pattern pattern;
    private Matcher matcher;
    private static final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d{2,})[a-zA-Z\\d]{1,}$"; 
    private String errorMessage;
    
    @Override
    public void initialize(ValidPassword constraintAnnotation) {  
    	errorMessage = constraintAnnotation.message();
    }
    
    @Override
    public boolean isValid(String email, ConstraintValidatorContext context){   
    	boolean isValid = validatePassword(email);
    	if(!isValid) {
    		context.disableDefaultConstraintViolation();
    		context.buildConstraintViolationWithTemplate( ": "+errorMessage  ).addConstraintViolation();
        }
        return isValid;
    } 
    
    private boolean validatePassword(String email) {
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }
    

}
