package ru.itis.utils.helpers;

import ru.itis.dto.oauth.VkTokenDto;
import ru.itis.dto.oauth.VkUserDto;

public interface OAuthHelper {

    VkUserDto getUserDtoByTokenDto(VkTokenDto dto);

    VkTokenDto getTokenDtoByCode(String code);

}
