package ru.itis.controllers;

import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<?> subscribe(@AuthenticationPrincipal UserDetailsImpl details,
                                            @PathVariable("user-id") UUID userId) {
        subscribeService.subscribe(details.getUser().getId(), userId);
        return ResponseEntity.accepted().build();
    }

    @PreAuthorize("isAuthenticated()")
    @PatchMapping("/unsubscribe/{user-id}")
    public ResponseEntity<?> unsubscribe(@AuthenticationPrincipal UserDetailsImpl details,
                                              @PathVariable("user-id") UUID userId) {
        subscribeService.unsubscribe(details.getUser().getId(), userId);
        return ResponseEntity.accepted().build();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/users/{user-id}/subscriptions")
    public String getSubscriptions(@PathVariable("user-id") UUID userId, ModelMap map) {
        map.put("accounts", subscribeService.getSubscriptionsById(userId));
        return "subscriptions";
    }
}
