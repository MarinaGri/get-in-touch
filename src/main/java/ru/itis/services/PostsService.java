package ru.itis.services;

import ru.itis.dto.FilterDto;
import ru.itis.dto.response.PostResponse;
import ru.itis.dto.form.PostForm;
import ru.itis.models.User;

import java.util.List;
import java.util.UUID;

public interface PostsService {
    List<PostResponse> getPostsByUserId(UUID userId);

    PostResponse addPost(User user, PostForm post);

    void deletePost(UUID userId, Long postId);

    PostResponse updatePost(User user, Long postId, PostForm post);

    List<PostResponse> getByUsersRating();

    List<PostResponse> getByUsersSkills();

    List<PostResponse> getByUsersActivities();

    List<PostResponse> getByFilter(FilterDto filter);
}
