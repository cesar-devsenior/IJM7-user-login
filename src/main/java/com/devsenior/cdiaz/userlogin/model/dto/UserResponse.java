package com.devsenior.cdiaz.userlogin.model.dto;

import java.time.LocalDate;

import com.devsenior.cdiaz.userlogin.model.shared.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserResponse {
    private String username;

    private String name;

    private String email;

    private LocalDate hireDate;

    private Boolean active;

    private Role role;
}
