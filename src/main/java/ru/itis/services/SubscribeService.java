package ru.itis.services;

import ru.itis.dto.UserDto;

import java.util.List;
import java.util.UUID;

public interface SubscribeService {
    List<UserDto> getSubscriptionsById(UUID id);

    void subscribe(UUID userId, UUID profileId);

    void unsubscribe(UUID userId, UUID profileId);

}
