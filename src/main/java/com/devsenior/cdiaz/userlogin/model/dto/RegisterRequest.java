package com.devsenior.cdiaz.userlogin.model.dto;

import org.hibernate.validator.constraints.Length;

import com.devsenior.cdiaz.userlogin.model.shared.Role;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegisterRequest {
    @NotBlank(message = "El nombre de usuario es obligatorio")
    @Length(min = 5, message = "El nombre de usuario debe tener mínimo 5 carácteres")
    private String username;

    @NotBlank(message = "La clave es obligatoria")
    @Length(min = 6, message = "La clave debe tener mínimo 6 carácteres")
    private String password;
    
    @JsonProperty("full_name")
    @JsonAlias({"name"})
    @NotBlank(message = "El nombre es obligatoria")
    private String fullName;
    
    @NotBlank(message = "El correo electrónico es obligatoria")
    @Email(message = "El correo electrónico no es válido")
    private String email;

    @NotNull(message = "El role del usuario es obligatoria")
    private Role role;


}
