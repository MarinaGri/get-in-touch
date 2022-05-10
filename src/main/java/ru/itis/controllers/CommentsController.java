package ru.itis.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.itis.dto.CommentDto;
import ru.itis.security.details.UserDetailsImpl;
import ru.itis.services.CommentsService;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/comments")
@RestController
public class CommentsController {

    private final CommentsService commentsService;

    @GetMapping
    public ResponseEntity<List<CommentDto>> getCommentsByVacancyId(@RequestParam("vacancy-id") Long vacancyId) {
        return ResponseEntity.ok(commentsService.getCommentsByVacancyId(vacancyId));
    }

    @PostMapping
    public ResponseEntity<CommentDto> addComment(@Valid @RequestBody CommentDto comment,
                                                 @AuthenticationPrincipal UserDetailsImpl details) {
        return new ResponseEntity<>(commentsService.addComment(details.getUser(), comment), HttpStatus.CREATED);
    }

    @DeleteMapping("/{comment-id}")
    public ResponseEntity<?> deleteComment(@PathVariable("comment-id") Long commentId,
                                           @AuthenticationPrincipal UserDetailsImpl details) {
        commentsService.deleteComment(details.getUser().getId(), commentId);
        return ResponseEntity.accepted().build();
    }

}
