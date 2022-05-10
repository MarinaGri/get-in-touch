package ru.itis.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.services.SubscribeService;
import ru.itis.dto.UserDto;
import ru.itis.models.User;
import ru.itis.services.UsersService;
import ru.itis.utils.mappers.UserMapper;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class SubscribeServiceImpl implements SubscribeService {

    private final UsersService usersService;

    private final UserMapper userMapper;

    @Override
    public List<UserDto> getSubscriptionsById(UUID id) {
        User user = usersService.getById(id);
        List<User> users = new ArrayList<>(user.getSubscriptions());
        return userMapper.toResponses(users);
    }

    @Transactional
    @Override
    public void subscribe(UUID userId, UUID profileId) {
        User user = usersService.getById(userId);
        user.getSubscriptions().add(usersService.getById(profileId));
    }

    @Transactional
    @Override
    public void unsubscribe(UUID userId, UUID profileId) {
        User user = usersService.getById(userId);
        user.getSubscriptions().remove(usersService.getById(profileId));
    }
}
