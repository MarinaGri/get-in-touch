package ru.itis.utils.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.itis.dto.SkillDto;
import ru.itis.models.Skill;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SkillMapper {

    SkillDto toResponse(Skill skill);

    List<SkillDto> toResponses(List<Skill> skills);

    @Mapping(target = "id", ignore = true)
    Skill toEntity(SkillDto dto);
}
