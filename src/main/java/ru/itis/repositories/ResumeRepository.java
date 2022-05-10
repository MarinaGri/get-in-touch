package ru.itis.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.itis.models.Resume;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ResumeRepository extends CrudRepository<Resume, Long> {

    Optional<List<Resume>> findByUser_Id(UUID userId);

    Optional<Resume> findByUser_IdAndId(UUID userId, Long id);

}
