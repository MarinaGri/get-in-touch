package ru.itis.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import ru.itis.exceptions.EntityNotFoundException;
import ru.itis.exceptions.NotHavePermissionsException;
import ru.itis.services.CommentsService;
import ru.itis.dto.CommentDto;
import ru.itis.models.Comment;
import ru.itis.models.User;
import ru.itis.repositories.CommentsRepository;
import ru.itis.utils.mappers.CommentMapper;

import java.util.*;
import java.util.function.Supplier;

@RequiredArgsConstructor
@Service
public class CommentsServiceImpl implements CommentsService {

    private final CommentsRepository commentsRepository;

    private final CommentMapper commentMapper;

    @Override
    public List<CommentDto> getCommentsByVacancyId(Long vacancyId) {
        Optional<List<Comment>> optionalComments = commentsRepository.findByVacancyId(vacancyId);
        return commentMapper.toResponses(optionalComments.orElse(new ArrayList<>()));
    }

    @Override
    public CommentDto addComment(User user, CommentDto newComment) {
        if (user.getState() == User.State.BANNED) {
            throw new NotHavePermissionsException("Banned users cannot post comments on jobs");
        }
        Comment comment = Comment.builder()
                .text(newComment.getText())
                .user(user)
                .vacancyId(newComment.getVacancyId())
                .build();
        return commentMapper.toResponse(commentsRepository.save(comment));
    }

    @Override
    public void deleteComment(UUID userId, Long commentId) {
        Optional<Comment> candidate = commentsRepository.findByUser_IdAndId(userId, commentId);

        Map<String, Object> map = new HashMap<>();
        map.put("id", commentId);
        map.put("user's id", userId);

        commentsRepository.delete(candidate.orElseThrow((Supplier<RuntimeException>) ()
                -> new EntityNotFoundException("User", map)));
    }
}
