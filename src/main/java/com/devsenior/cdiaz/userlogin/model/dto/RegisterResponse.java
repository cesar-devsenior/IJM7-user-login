package com.devsenior.cdiaz.userlogin.model.dto;

import java.time.LocalDate;

import com.devsenior.cdiaz.userlogin.model.shared.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class RegisterResponse {
    private String username;

    private String name;

    private String email;

    private LocalDate hireDate;

    private Boolean active;

    private Role role;
}
