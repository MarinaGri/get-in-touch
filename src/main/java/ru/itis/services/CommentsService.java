package ru.itis.services;

import ru.itis.dto.CommentDto;
import ru.itis.models.User;

import java.util.List;
import java.util.UUID;

public interface CommentsService {

    List<CommentDto> getCommentsByVacancyId(Long vacancyId);

    CommentDto addComment(User user, CommentDto comment);

    void deleteComment(UUID userId, Long commentId);
}
