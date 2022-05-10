package ru.itis.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.itis.dto.ResumeDto;
import ru.itis.security.details.UserDetailsImpl;
import ru.itis.services.ResumeService;

import javax.validation.Valid;
import java.util.UUID;

@RequiredArgsConstructor
@Controller
public class ResumeController {

    private final ResumeService resumeService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/resumes/edit")
    public String getResumeEditPage(ModelMap map) {
        map.put("form", new ResumeDto());
        return "resume_edit";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/resumes/edit")
    public String addResume(@ModelAttribute("form") @Valid ResumeDto form, BindingResult result,
                            @AuthenticationPrincipal UserDetailsImpl details, ModelMap map,
                            RedirectAttributes redirectAttributes) {
        map.put("form", form);
        if (result.hasErrors()) {
            return "resume_edit";
        }
        resumeService.addResume(details.getUser().getId(), form);
        redirectAttributes.addFlashAttribute("success", "success.resume-edit");
        return "redirect:edit";
    }

    @DeleteMapping("/resumes/{resume-id}")
    public ResponseEntity<?> deleteResumeById(@PathVariable("resume-id") Long id,
                                              @AuthenticationPrincipal UserDetailsImpl details) {
        resumeService.deleteResume(details.getUser().getId(), id);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/users/{user-id}/resumes")
    public String getResumesByUserId(@PathVariable("user-id") UUID userId, ModelMap map,
                                     @AuthenticationPrincipal UserDetailsImpl details) {
        map.put("id", details.getUser().getId());
        map.put("resumes", resumeService.getAllByUserId(userId));
        return "resumes";
    }
}
