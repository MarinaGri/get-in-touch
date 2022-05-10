package ru.itis.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.itis.models.Comment;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CommentsRepository extends CrudRepository<Comment, Long> {

    Optional<Comment> findByUser_IdAndId(UUID userId, Long id);

    Optional<List<Comment>> findByVacancyId(Long id);

}
