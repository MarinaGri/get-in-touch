package ru.itis.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.itis.security.details.UserDetailsImpl;
import ru.itis.services.SubscribeService;

import java.util.UUID;

@RequiredArgsConstructor
@Controller
public class SubscribeController {

    private final SubscribeService subscribeService;

    @PreAuthorize("isAuthenticated()")
    @PatchMapping("/subscribe/{user-id}")
    public ResponseEntity<Object> subscribe(@AuthenticationPrincipal UserDetailsImpl details,
                                            @PathVariable("user-id") UUID userId) {
        subscribeService.subscribe(details.getUser().getId(), userId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PreAuthorize("isAuthenticated()")
    @PatchMapping("/unsubscribe/{user-id}")
    public ResponseEntity<Object> unsubscribe(@AuthenticationPrincipal UserDetailsImpl details,
                                              @PathVariable("user-id") UUID userId) {
        subscribeService.unsubscribe(details.getUser().getId(), userId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/users/{user-id}/subscriptions")
    public String getSubscriptions(@PathVariable("user-id") UUID userId, ModelMap map) {
        map.put("accounts", subscribeService.getSubscriptionsById(userId));
        return "subscriptions";
    }
}
