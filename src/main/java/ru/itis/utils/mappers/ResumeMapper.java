package ru.itis.utils.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.itis.models.Resume;
import ru.itis.models.Skill;
import ru.itis.models.User;
import ru.itis.dto.ResumeDto;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", imports = {User.class, Skill.class, Collectors.class})
public interface ResumeMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", expression = "java(User.builder().id(resume.getUserId()).build())")
    @Mapping(target = "skills", expression = "java(resume.getSkills()" +
                    ".stream().map(name -> new Skill(name.toLowerCase()))" +
                    ".collect(Collectors.toSet()))")
    Resume toEntity(ResumeDto resume);

    @Mapping(target = "userId", expression = "java(resume.getUser().getId())")
    @Mapping(target = "skills", expression = "java(resume.getSkills()" +
            ".stream().map(skill -> skill.getName())" +
            ".collect(Collectors.toList()))")
    ResumeDto toResponse(Resume resume);

    List<ResumeDto> toResponses(List<Resume> resumes);

}
