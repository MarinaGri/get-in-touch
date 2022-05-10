package ru.itis.validation.validators;

import ru.itis.validation.annotations.MobileNumber;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class MobileNumberValidator implements ConstraintValidator<MobileNumber, String> {

    private final String REGEX = "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (!Pattern.compile(REGEX).matcher(value).matches()) {
            context.buildConstraintViolationWithTemplate("{validation.mobile-number}").addConstraintViolation();
            return false;
        }
        return true;
    }
}
