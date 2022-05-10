package ru.itis.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.exceptions.EntityNotFoundException;
import ru.itis.services.FilesService;
import ru.itis.services.UsersService;
import ru.itis.dto.UserDto;
import ru.itis.dto.form.UserForm;
import ru.itis.models.User;
import ru.itis.repositories.UsersRepository;
import ru.itis.utils.mappers.UserMapper;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;

@RequiredArgsConstructor
@Service
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;

    private final FilesService filesService;

    private final UserMapper userMapper;


    @Override
    public UserDto getUserById(UUID id) {
        User user = getById(id);
        return userMapper.toResponse(user);
    }

    @Transactional
    @Override
    public void updateUser(UUID userId, UserForm updateUser, MultipartFile avatar) {
        User user = getById(userId);
        if (!avatar.isEmpty()) {
            user.setAvatar(filesService.upload(avatar));
        } else {
            user.setAvatar(null);
        }
        user.setFirstName(updateUser.getFirstName());
        user.setLastName(updateUser.getLastName());
    }

    @Override
    public User getById(UUID id) {
        return usersRepository.findUserById(id).orElseThrow((Supplier<RuntimeException>) ()
                -> new EntityNotFoundException("User", Collections.singletonMap("id", String.valueOf(id))));

    }

    @Override
    public void delete(UUID id) {
        usersRepository.deleteById(id);
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userMapper.toResponses(usersRepository.findAll());
    }

    @Transactional
    @Override
    public UserDto unbanUser(UUID userId) {
        User user = getById(userId);
        user.setState(User.State.ACTIVE);
        return userMapper.toResponse(user);
    }

    @Transactional
    @Override
    public void banUser(UUID userId) {
        User user = getById(userId);
        user.setState(User.State.BANNED);
    }
}
