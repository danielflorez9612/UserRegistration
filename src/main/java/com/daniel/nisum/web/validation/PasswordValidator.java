package com.daniel.nisum.web.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<ValidPassword, String>{

    private static final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d{2,})[a-zA-Z\\d]+$";

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context){   
        return validatePassword(email);
    } 
    
    private static boolean validatePassword(String email) {
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    

}
