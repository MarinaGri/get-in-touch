package ru.itis.services;

import ru.itis.dto.SkillDto;

import java.util.List;

public interface SkillsService {

    List<SkillDto> getSkills();

    SkillDto updateSkill(Long id, SkillDto skill);

    SkillDto addSkill(SkillDto newSkill);

    void deleteSkill(Long id);

    SkillDto getSkillById(Long id);
}
