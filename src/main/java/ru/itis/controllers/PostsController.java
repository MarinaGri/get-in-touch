package ru.itis.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.itis.dto.response.PostResponse;
import ru.itis.dto.form.PostForm;
import ru.itis.security.details.UserDetailsImpl;
import ru.itis.services.PostsService;

import javax.validation.Valid;


@RequiredArgsConstructor
@RequestMapping("/posts")
@RestController
public class PostsController {

    private final PostsService postsService;

    @PreAuthorize("isAuthenticated()")
    @PutMapping
    public ResponseEntity<PostResponse> addPost(@Valid @RequestBody PostForm post,
                                                @AuthenticationPrincipal UserDetailsImpl details) {
        return new ResponseEntity<>(
                postsService.addPost(details.getUser(), post),
                HttpStatus.CREATED
        );
    }

    @PreAuthorize("isAuthenticated()")
    @PatchMapping("/{post-id}")
    public ResponseEntity<PostResponse> updatePost(@Valid @RequestBody PostForm post,
                                                   @PathVariable("post-id") Long postId,
                                                   @AuthenticationPrincipal UserDetailsImpl details) {
        return new ResponseEntity<>(
                postsService.updatePost(details.getUser(), postId, post),
                HttpStatus.ACCEPTED
        );
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{post-id}")
    public ResponseEntity<?> deletePost(@PathVariable("post-id") Long postId,
                                        @AuthenticationPrincipal UserDetailsImpl details) {
        postsService.deletePost(details.getUser().getId(), postId);
        return ResponseEntity.accepted().build();
    }

}
