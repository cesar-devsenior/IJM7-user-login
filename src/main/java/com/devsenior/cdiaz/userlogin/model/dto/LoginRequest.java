package com.devsenior.cdiaz.userlogin.model.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
    @NotBlank(message = "El nombre de usuario es obligatorio")
    @Length(min = 5, message = "El nombre de usuario debe tener mínimo 5 carácteres")
    private String username;

    @NotBlank(message = "La clave es obligatoria")
    @Length(min = 6, message = "La clave debe tener mínimo 6 carácteres")
    private String password;
}
