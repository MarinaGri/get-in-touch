package ru.itis.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.dto.form.VacancyForm;
import ru.itis.security.details.UserDetailsImpl;
import ru.itis.services.VacanciesService;


@RequiredArgsConstructor
@Controller
public class SearchController {

    private final VacanciesService vacanciesService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/search")
    public String getVacanciesPage(ModelMap map, VacancyForm vacancy, Integer page,
                                   @AuthenticationPrincipal UserDetailsImpl details) {
        map.put("id", details.getUser().getId());
        map.put("page", vacanciesService.getVacanciesPage(vacancy, page));
        map.put("vacancyForm", vacancy);
        return "search";
    }
}
