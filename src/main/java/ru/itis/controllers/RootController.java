package ru.itis.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.security.details.UserDetailsImpl;

@Controller
public class RootController {

    @GetMapping("/")
    public String getDefault(@AuthenticationPrincipal UserDetailsImpl details) {
        if (details != null) {
            return "redirect:profile?id=" + details.getUser().getId();
        } else {
            return "redirect:signIn";
        }
    }
}
