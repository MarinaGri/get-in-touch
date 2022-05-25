package ru.itis.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.dto.SkillDto;
import ru.itis.exceptions.EntityNotFoundException;
import ru.itis.models.Skill;
import ru.itis.repositories.SkillsRepository;
import ru.itis.services.SkillsService;
import ru.itis.utils.mappers.SkillMapper;
import javax.transaction.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

@RequiredArgsConstructor
@Service
public class SkillsServiceImpl implements SkillsService {

    private final SkillsRepository skillsRepository;

    private final SkillMapper skillMapper;

    @Override
    public List<SkillDto> getSkills() {
        return skillMapper.toResponses(skillsRepository.findAll());
    }

    @Transactional
    @Override
    public SkillDto updateSkill(Long id, SkillDto updateSkill) {
        Skill skill = getById(id);
        skill.setName(updateSkill.getName());
        return skillMapper.toResponse(skill);
    }

    @Override
    public SkillDto addSkill(SkillDto newSkill) {
        Skill skill = skillMapper.toEntity(newSkill);
        return skillMapper.toResponse(skillsRepository.save(skill));
    }

    @Transactional
    @Override
    public void deleteSkill(Long id) {
        skillsRepository.delete(getById(id));
    }

    @Override
    public SkillDto getSkillById(Long id) {
        return skillMapper.toResponse(getById(id));
    }

    private Skill getById(Long id){
        return skillsRepository.findById(id).orElseThrow((Supplier<RuntimeException>) ()
                -> new EntityNotFoundException("Skill", Collections.singletonMap("id", String.valueOf(id))));
    }
}
