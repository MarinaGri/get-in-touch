package ru.itis.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.itis.dto.UserDto;
import ru.itis.dto.form.SignUpForm;
import ru.itis.services.SignUpService;
import ru.itis.services.UsersService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UsersController {

    private final UsersService usersService;

    private final SignUpService signUpService;

    @PutMapping
    public ResponseEntity<UserDto> addUser(@Valid @RequestBody SignUpForm form) {
        return new ResponseEntity<>(signUpService.signUp(form), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers() {
        return ResponseEntity.ok(usersService.getAllUsers());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PatchMapping("/{user-id}")
    public ResponseEntity<UserDto> unbanUser(@PathVariable("user-id") UUID userId) {
        return new ResponseEntity<>(usersService.unbanUser(userId), HttpStatus.ACCEPTED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{user-id}")
    public ResponseEntity<?> banUser(@PathVariable("user-id") UUID userId) {
        usersService.banUser(userId);
        return ResponseEntity.accepted().build();
    }
}
