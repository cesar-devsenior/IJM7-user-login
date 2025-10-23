package com.devsenior.cdiaz.userlogin.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.devsenior.cdiaz.userlogin.model.dto.LoginRequest;
import com.devsenior.cdiaz.userlogin.model.dto.LoginResponse;
import com.devsenior.cdiaz.userlogin.model.dto.RegisterRequest;
import com.devsenior.cdiaz.userlogin.model.dto.RegisterResponse;
import com.devsenior.cdiaz.userlogin.service.AuthenticationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody @Valid LoginRequest body) {
        return authenticationService.login(body);
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping("/register")
    public RegisterResponse register(@RequestBody @Valid RegisterRequest body) {
        return authenticationService.register(body);
    }

}
