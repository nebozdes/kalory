package com.matveev.kalory.api;

import com.matveev.kalory.model.User;
import com.matveev.kalory.service.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final SecurityService service;

    @GetMapping("/me")
    public ResponseEntity<User> current() {
        return ResponseEntity.ok(service.getCurrentUser());
    }
}
