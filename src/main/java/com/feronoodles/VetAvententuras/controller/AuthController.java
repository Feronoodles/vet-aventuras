package com.feronoodles.VetAvententuras.controller;


import com.feronoodles.VetAvententuras.domain.employee.dto.EmployeeRegisterDTO;
import com.feronoodles.VetAvententuras.domain.employee.dto.LoginRequest;
import com.feronoodles.VetAvententuras.domain.token.dto.RefreshTokenRequest;
import com.feronoodles.VetAvententuras.infra.security.AutenticationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AutenticationService authService;
    @PostMapping(value = "login")
    public ResponseEntity login(@RequestBody @Valid LoginRequest request)
    {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping(value = "register")
    public ResponseEntity register(@RequestBody @Valid EmployeeRegisterDTO request)
    {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping(value = "refresh")
    public ResponseEntity refreshToken(@RequestBody @Valid RefreshTokenRequest request)
    {
        return ResponseEntity.ok(authService.refreshToken(request));
    }



}
