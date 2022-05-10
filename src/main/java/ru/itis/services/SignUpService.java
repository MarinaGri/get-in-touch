package ru.itis.services;

import ru.itis.exceptions.DuplicateEmailException;
import ru.itis.dto.UserDto;
import ru.itis.dto.form.SignUpForm;

public interface SignUpService {

    UserDto signUp(SignUpForm form) throws DuplicateEmailException;
}
