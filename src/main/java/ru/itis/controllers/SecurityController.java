package ru.itis.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.dto.form.SignInForm;
import ru.itis.security.details.UserDetailsImpl;

import javax.servlet.http.HttpServletRequest;


@RequiredArgsConstructor
@Controller
public class SecurityController {

    @GetMapping({"/signIn", "/oauth"})
    public String getSignInPage(@AuthenticationPrincipal UserDetailsImpl details, ModelMap map,
                                HttpServletRequest request) {
        if (details != null) {
            return "redirect:profile?id=" + details.getUser().getId();
        }
        map.put("error", request.getParameterMap().containsKey("error"));
        map.put("form", new SignInForm());
        return "sign_in";
    }

    @GetMapping("/forbidden")
    public String forbidden(ModelMap map) {
        map.put("status", HttpStatus.FORBIDDEN.value());
        map.put("error", "Access to this resource on the server is denied!");
        return "error";
    }

}
