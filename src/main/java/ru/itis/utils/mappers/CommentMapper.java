package ru.itis.utils.mappers;

import org.mapstruct.Mapper;
import ru.itis.models.Comment;
import ru.itis.dto.CommentDto;

import java.util.List;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface CommentMapper {

    CommentDto toResponse(Comment comment);

    List<CommentDto> toResponses(List<Comment> comments);

}
