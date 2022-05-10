package ru.itis.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.dto.form.UserForm;
import ru.itis.security.details.UserDetailsImpl;
import ru.itis.services.UsersService;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/profile/edit")
@Controller
public class ProfileEditController {

    private final UsersService usersService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String editProfile(
            @ModelAttribute("form") @Valid UserForm form, BindingResult result,
            @RequestPart(required = false) MultipartFile file, ModelMap map,
            @AuthenticationPrincipal UserDetailsImpl details) {

        if (result.hasErrors()) {
            map.put("form", form);
            return "profile_edit";
        }
        usersService.updateUser(details.getUser().getId(), form, file);
        return "redirect:/profile/edit";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public String getEditPage(ModelMap map, @AuthenticationPrincipal UserDetailsImpl details) {
        map.put("form", UserForm.builder()
                .firstName(details.getUser().getFirstName())
                .lastName(details.getUser().getLastName())
                .build());
        return "profile_edit";
    }
}
