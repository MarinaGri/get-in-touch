package ru.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.models.Skill;

import java.util.List;
import java.util.Optional;

public interface SkillsRepository extends JpaRepository<Skill, Long> {

    Optional<List<Skill>> findByNameIn(List<String> names);

}
