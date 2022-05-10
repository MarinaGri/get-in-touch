package ru.itis.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.itis.exceptions.EntityNotFoundException;
import ru.itis.services.PostsService;
import ru.itis.dto.FilterDto;
import ru.itis.dto.response.PostResponse;
import ru.itis.dto.form.PostForm;
import ru.itis.models.Post;
import ru.itis.models.User;
import ru.itis.repositories.PostsRepository;
import ru.itis.repositories.filters.PostSpecification;
import ru.itis.utils.mappers.PostMapper;

import java.util.*;
import java.util.function.Supplier;

@RequiredArgsConstructor
@Service
public class PostsServiceImpl implements PostsService {

    private final PostsRepository postsRepository;

    private final PostMapper postMapper;

    @Value("${app.limit.top-users}")
    private int limit;

    @Override
    public List<PostResponse> getPostsByUserId(UUID userId) {
        return processResponse(postsRepository.findByUser_Id(userId));
    }

    @Override
    public PostResponse addPost(User user, PostForm post) {
        Post newPost = Post.builder()
                .title(post.getTitle())
                .text(post.getText())
                .user(user)
                .build();
        return postMapper.toResponse(postsRepository.save(newPost));
    }

    @Override
    public void deletePost(UUID userId, Long postId) {
        Optional<Post> candidate = postsRepository.findByUser_IdAndId(userId, postId);

        Map<String, Object> map = new HashMap<>();
        map.put("id", postId);
        map.put("user's id", userId);

        postsRepository.delete(candidate.orElseThrow((Supplier<RuntimeException>) ()
                -> new EntityNotFoundException("Post", map)));
    }

    @Override
    public PostResponse updatePost(User user, Long postId, PostForm post) {
        Post updatePost = Post.builder()
                .id(postId)
                .title(post.getTitle())
                .text(post.getText())
                .user(user)
                .build();
        return postMapper.toResponse(postsRepository.save(updatePost));
    }

    @Override
    public List<PostResponse> getByUsersRating() {
        return processResponse(postsRepository.findByUserRating(limit));
    }

    @Override
    public List<PostResponse> getByUsersSkills() {
        return processResponse(postsRepository.findByUserSkills());
    }

    @Override
    public List<PostResponse> getByUsersActivities() {
        return processResponse(postsRepository.findByUserActivities(limit));
    }

    @Override
    public List<PostResponse> getByFilter(FilterDto filter) {
        Specification<Post> specification = null;
        if (filter.getTitle() != null && !filter.getTitle().equals("")) {
            specification = PostSpecification.byFieldLike("title", filter.getTitle());
        }

        if (filter.getText() != null && !filter.getText().equals("")) {
            specification = PostSpecification.byFieldLike("text", filter.getText())
                    .and(specification);
        }

        if (filter.getFirstName() != null && !filter.getFirstName().equals("")) {
            specification = PostSpecification.byUserFieldEqual("firstName", filter.getFirstName())
                    .and(specification);
        }

        if (filter.getLastName() != null && !filter.getLastName().equals("")) {
            specification = PostSpecification.byUserFieldEqual("lastName", filter.getLastName())
                    .and(specification);
        }

        return postMapper.toResponses(postsRepository.findAll(specification));
    }

    private List<PostResponse> processResponse(Optional<List<Post>> optionalPosts) {
        return postMapper.toResponses(optionalPosts.orElseGet(ArrayList::new));
    }
}
