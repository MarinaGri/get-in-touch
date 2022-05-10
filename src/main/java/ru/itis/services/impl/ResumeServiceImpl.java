package ru.itis.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.exceptions.EntityNotFoundException;
import ru.itis.services.ResumeService;
import ru.itis.dto.ResumeDto;
import ru.itis.models.Resume;
import ru.itis.models.Skill;
import ru.itis.repositories.ResumeRepository;
import ru.itis.repositories.SkillsRepository;
import ru.itis.utils.mappers.ResumeMapper;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ResumeServiceImpl implements ResumeService {

    private final ResumeRepository resumeRepository;

    private final SkillsRepository skillsRepository;

    private final ResumeMapper resumeMapper;

    @Override
    public void addResume(UUID userId, ResumeDto resume) {
        resume.setUserId(userId);
        List<String> skills = resume.getSkills().stream()
                .map(String::toLowerCase)
                .filter(skill -> !skill.equals(""))
                .distinct()
                .collect(Collectors.toList());

        Optional<List<Skill>> candidates = skillsRepository.findByNameIn(skills);
        List<Skill> repeatedSkills = new ArrayList<>();
        if(candidates.isPresent()) {
            for (Skill skill : candidates.get()) {
                skills.remove(skill.getName());
                repeatedSkills.add(skill);
            }
        }
        resume.setSkills(skills);

        Resume newResume = resumeMapper.toEntity(resume);
        newResume.getSkills().addAll(repeatedSkills);

        resumeRepository.save(newResume);
    }

    @Override
    public List<ResumeDto> getAllByUserId(UUID userId) {
        Optional<List<Resume>> optionalPosts = resumeRepository.findByUser_Id(userId);
        return resumeMapper.toResponses(optionalPosts.orElseGet(ArrayList::new));
    }

    @Override
    public void deleteResume(UUID userId, Long id) {
        Optional<Resume> candidate = resumeRepository.findByUser_IdAndId(userId, id);

        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("user's id", userId);

        resumeRepository.delete(candidate.orElseThrow((Supplier<RuntimeException>) ()
                -> new EntityNotFoundException("Resume", map)));
    }
}
