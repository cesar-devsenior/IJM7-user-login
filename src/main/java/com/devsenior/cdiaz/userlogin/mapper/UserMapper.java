package com.devsenior.cdiaz.userlogin.mapper;

import java.time.LocalDate;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.devsenior.cdiaz.userlogin.model.dto.RegisterRequest;
import com.devsenior.cdiaz.userlogin.model.dto.RegisterResponse;
import com.devsenior.cdiaz.userlogin.model.entity.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserMapper {

    private final PasswordEncoder passwordEncoder;

    public User toEntity(RegisterRequest request) {
        return User.builder()
                .active(true)
                .hireDate(LocalDate.now())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getFullName())
                .email(request.getEmail())
                .role(request.getRole())
                .build();
    }

    public RegisterResponse toRespose(User user) {
        return RegisterResponse.builder()
                .username(user.getUsername())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole())
                .hireDate(user.getHireDate())
                .active(user.getActive())
                .build();
    }
}
