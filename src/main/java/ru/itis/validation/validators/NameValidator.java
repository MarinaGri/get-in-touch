package ru.itis.validation.validators;

import ru.itis.validation.annotations.Name;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class NameValidator implements ConstraintValidator<Name, String> {

    private final String REGEX = "(?:\\w|[а-яА-ЯA-Z])+";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (!Pattern.compile(REGEX).matcher(value).matches()) {
            context.buildConstraintViolationWithTemplate("{validation.name}").addConstraintViolation();
            return false;
        }
        return true;
    }
}
