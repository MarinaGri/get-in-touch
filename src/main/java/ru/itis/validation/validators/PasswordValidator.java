package ru.itis.validation.validators;

import ru.itis.validation.annotations.Password;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;


public class PasswordValidator implements ConstraintValidator<Password, String> {
    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        boolean isValid = true;

        if (password.length() < 5) {
            isValid = false;
            context.buildConstraintViolationWithTemplate("{validation.password.length}").addConstraintViolation();
        }

        if (!Pattern.compile("[0-9]").matcher(password).find()) {
            isValid = false;
            context.buildConstraintViolationWithTemplate("{validation.password.digit}").addConstraintViolation();
        }

        if (!Pattern.compile("[A-Z]").matcher(password).find()) {
            isValid = false;
            context.buildConstraintViolationWithTemplate("{validation.password.capital-letter}").addConstraintViolation();
        }

        if (!Pattern.compile("[a-z]").matcher(password).find()) {
            isValid = false;
            context.buildConstraintViolationWithTemplate("{validation.password.lowercase-letter}").addConstraintViolation();
        }

        return isValid;
    }
}
