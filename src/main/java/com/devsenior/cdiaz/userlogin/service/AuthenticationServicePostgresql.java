package com.devsenior.cdiaz.userlogin.service;

import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.devsenior.cdiaz.userlogin.mapper.UserMapper;
import com.devsenior.cdiaz.userlogin.model.dto.LoginRequest;
import com.devsenior.cdiaz.userlogin.model.dto.LoginResponse;
import com.devsenior.cdiaz.userlogin.model.dto.RegisterRequest;
import com.devsenior.cdiaz.userlogin.model.dto.RegisterResponse;
import com.devsenior.cdiaz.userlogin.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AuthenticationServicePostgresql implements AuthenticationService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;

    @Override
    public RegisterResponse register(RegisterRequest userInfo) {
        // TODO: Verificar que el username no exista en la base de datos;
        // TODO: Verificar que el email no exista en la base de datos;

        // Creo una entidad con los datos del request
        var user = userMapper.toEntity(userInfo);

        // Guardo en la base de datos
        user = userRepository.save(user);

        // Convierto el objeto guardado a response
        var response = userMapper.toRespose(user);

        // Devuelvo el response
        return response;
    }

    @Override
    public LoginResponse login(LoginRequest credentials) {
        // 1. Validar usuario y contraseña - Spring Security
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        credentials.getUsername(),
                        credentials.getPassword()));

        // 2. Generar el token
        var userDetails = userDetailsService.loadUserByUsername(credentials.getUsername());
        var userInfo = userRepository.findById(credentials.getUsername());
        Map<String, Object> claims = Map.of();
        if (userInfo.isPresent()) {
            claims = Map.<String, Object>of(
                    "name", userInfo.get().getName(),
                    "role", userInfo.get().getRole().toString());
        }
        var token = jwtService.generateToken(claims, userDetails);

        // 3. Devolver la respuesta
        return LoginResponse.builder()
                .jwt(token)
                .build();
    }

}
