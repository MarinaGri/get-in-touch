package ru.itis.validation.validators;

import ru.itis.validation.annotations.Naming;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class NamingValidator implements ConstraintValidator<Naming, String> {

    private final String REGEX = "(?:(?:\\w|[а-яА-ЯA-Z-])+\\s?(?:\\w|[а-яА-ЯA-Z])*\\s?)+";

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) {

        if (!Pattern.compile(REGEX).matcher(name).matches()) {
            context.buildConstraintViolationWithTemplate("{validation.naming}").addConstraintViolation();
            return false;
        }
        return true;
    }
}
