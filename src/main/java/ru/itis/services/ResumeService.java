package ru.itis.services;

import ru.itis.dto.ResumeDto;

import java.util.List;
import java.util.UUID;

public interface ResumeService {

    void addResume(UUID userId, ResumeDto resume);

    List<ResumeDto> getAllByUserId(UUID userId);

    void deleteResume(UUID userId, Long id);
}
