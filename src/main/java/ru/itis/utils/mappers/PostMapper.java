package ru.itis.utils.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import ru.itis.models.Post;
import ru.itis.dto.response.PostResponse;
import ru.itis.utils.helpers.HtmlCleaner;

import java.util.List;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public abstract class PostMapper {

    @Autowired
    protected HtmlCleaner htmlCleaner;

    @Mapping(target = "text", expression = "java(htmlCleaner.keepOnlySafeTags(post.getText()))")
    public abstract PostResponse toResponse(Post post);

    public abstract List<PostResponse> toResponses(List<Post> posts);
}
