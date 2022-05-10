package ru.itis.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itis.dto.form.PostForm;
import ru.itis.security.details.UserDetailsImpl;
import ru.itis.services.PostsService;
import ru.itis.services.UsersService;

import java.util.UUID;


@RequiredArgsConstructor
@RequestMapping("/profile")
@Controller
public class ProfileController {

    private final UsersService usersService;

    private final PostsService postsService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping()
    public String getProfile(@AuthenticationPrincipal UserDetailsImpl details) {
        return "redirect:profile/" + details.getUser().getId();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{user-id}")
    public String getProfilePage(@AuthenticationPrincipal UserDetailsImpl details,
                                 @PathVariable("user-id") UUID id, Model model) {
        UUID userId = details.getUser().getId();
        model.addAttribute("posts", postsService.getPostsByUserId(id));

        if (userId.equals(id)) {
            model.addAttribute("user", usersService.getUserById(id));
            model.addAttribute("form", new PostForm());
            return "profile";
        } else {
            model.addAttribute("profile", usersService.getUserById(id));
            model.addAttribute("user", usersService.getUserById(userId));
            return "another_profile";
        }
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteAccount(@AuthenticationPrincipal UserDetailsImpl details){
        usersService.delete(details.getUser().getId());
        return ResponseEntity.accepted().build();
    }
}
