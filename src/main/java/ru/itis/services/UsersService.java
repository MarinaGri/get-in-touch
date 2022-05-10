package ru.itis.services;

import org.springframework.web.multipart.MultipartFile;
import ru.itis.dto.UserDto;
import ru.itis.dto.form.UserForm;
import ru.itis.models.User;

import java.util.List;
import java.util.UUID;

public interface UsersService {

    UserDto getUserById(UUID id);

    void updateUser(UUID userId, UserForm user, MultipartFile avatar);

    User getById(UUID id);

    void delete(UUID id);

    List<UserDto> getAllUsers();

    UserDto unbanUser(UUID userId);

    void banUser(UUID userId);
}
