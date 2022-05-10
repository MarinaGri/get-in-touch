package ru.itis.utils.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.itis.models.User;
import ru.itis.dto.UserDto;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = FileInfoMapper.class, imports = Collectors.class)
public interface UserMapper {

    @Mapping(target = "avatarLink", source = "avatar")
    @Mapping(target = "subs", expression = "java(user.getSubscriptions().stream()" +
                    ".map(x -> (x.getId().toString()))" +
                    ".collect(Collectors.toList()))")
    @Mapping(target = "id", expression = "java(user.getId().toString())")
    @Mapping(target = "numSubs", expression = "java(user.getSubscriptions().size())")
    UserDto toResponse(User user);

    List<UserDto> toResponses(List<User> users);

}
