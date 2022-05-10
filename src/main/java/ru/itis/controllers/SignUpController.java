package ru.itis.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.dto.form.SignUpForm;
import ru.itis.exceptions.DuplicateEmailException;
import ru.itis.services.SignUpService;

import javax.validation.Valid;


@RequiredArgsConstructor
@Controller
public class SignUpController {

    private final SignUpService signUpService;

    @GetMapping("/signUp")
    public String getSingUpPage(Authentication authentication, Model model) {
        if (authentication != null) {
            return "redirect:profile";
        }
        model.addAttribute("form", new SignUpForm());
        return "sign_up";
    }

    @PostMapping("/signUp")
    public String signUp(@ModelAttribute("form") @Valid SignUpForm form, BindingResult result, ModelMap map) {
        if (result.hasErrors()) {
            map.put("form", form);
            return "sign_up";
        }
        try {
            signUpService.signUp(form);
        } catch (DuplicateEmailException ex) {
            map.put("form", form);
            result.rejectValue("email", "error.email.duplicate" );
            return "sign_up";
        }
        return "redirect:/signIn";
    }

}
