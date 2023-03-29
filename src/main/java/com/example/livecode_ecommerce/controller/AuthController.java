package com.example.livecode_ecommerce.controller;

import com.example.livecode_ecommerce.model.dto.LoginRequest;
import com.example.livecode_ecommerce.model.dto.RegisterRequest;
import com.example.livecode_ecommerce.model.response.SuccessResponse;
import com.example.livecode_ecommerce.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterRequest registerRequest) {
        String token = authService.register(registerRequest);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new SuccessResponse<String>("Success registration", token));
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequest loginRequest) {
        String token = authService.login(loginRequest);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<String>("Success login", token));
    }
}
