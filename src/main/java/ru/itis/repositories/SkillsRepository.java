package ru.itis.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.itis.models.Skill;

import java.util.List;
import java.util.Optional;

public interface SkillsRepository extends CrudRepository<Skill, Long> {

    Optional<List<Skill>> findByNameIn(List<String> names);

}
