package ru.itis.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.dto.SkillDto;
import ru.itis.services.SkillsService;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/skills")
@RestController
public class SkillsController {

    private final SkillsService skillsService;

    @PostMapping
    public ResponseEntity<SkillDto> addSkill(@Valid @RequestBody SkillDto skill) {
        return new ResponseEntity<>(skillsService.addSkill(skill), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<SkillDto>> getSkills() {
        return ResponseEntity.ok(skillsService.getSkills());
    }

    @GetMapping("/{skill-id}")
    public ResponseEntity<SkillDto> getSkill(@PathVariable("skill-id") Long id) {
        return ResponseEntity.ok(skillsService.getSkillById(id));
    }

    @PutMapping("/{skill-id}")
    public ResponseEntity<SkillDto> updateSkill(@PathVariable("skill-id") Long id,
                                                @Valid @RequestBody SkillDto skill) {
        return new ResponseEntity<>(skillsService.updateSkill(id, skill), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{skill-id}")
    public ResponseEntity<?> deleteSkill(@PathVariable("skill-id") Long id) {
        skillsService.deleteSkill(id);
        return ResponseEntity.accepted().build();
    }
}
