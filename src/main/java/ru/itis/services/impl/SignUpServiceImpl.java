package ru.itis.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.exceptions.DuplicateEmailException;
import ru.itis.services.SignUpService;
import ru.itis.dto.UserDto;
import ru.itis.dto.form.SignUpForm;
import ru.itis.models.User;
import ru.itis.repositories.UsersRepository;
import ru.itis.utils.mappers.UserMapper;

import java.util.HashSet;

@RequiredArgsConstructor
@Service
public class SignUpServiceImpl implements SignUpService {

    private final PasswordEncoder passwordEncoder;

    private final UsersRepository usersRepository;

    private final UserMapper userMapper;

    @Override
    public UserDto signUp(SignUpForm form) throws DuplicateEmailException {
        if (usersRepository.findByEmail(form.getEmail()).isPresent()) {
            throw new DuplicateEmailException("User with " + form.getEmail() + " email already exists");
        }

        User user = User.builder()
                .firstName(form.getFirstName())
                .lastName(form.getLastName())
                .password(passwordEncoder.encode(form.getPassword()))
                .email(form.getEmail())
                .subscriptions(new HashSet<>())
                .role(User.Role.ROLE_USER)
                .state(User.State.ACTIVE)
                .build();

        return userMapper.toResponse(usersRepository.save(user));
    }
}
