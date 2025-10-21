package com.devsenior.cdiaz.userlogin.model.entity;

import java.time.LocalDate;

import com.devsenior.cdiaz.userlogin.model.shared.Role;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    private String username;

    private String name;

    private String email;

    private String password;

    private LocalDate hireDate;

    private Boolean active;

    private Role role;

}
