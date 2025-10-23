package com.devsenior.cdiaz.userlogin.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.devsenior.cdiaz.userlogin.exception.BadLoginException;
import com.devsenior.cdiaz.userlogin.mapper.UserMapper;
import com.devsenior.cdiaz.userlogin.model.dto.LoginRequest;
import com.devsenior.cdiaz.userlogin.model.dto.RegisterRequest;
import com.devsenior.cdiaz.userlogin.model.dto.RegisterResponse;
import com.devsenior.cdiaz.userlogin.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AuthenticationServicePostgresql implements AuthenticationService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

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
    public void login(LoginRequest credentials) {
        userRepository.findById(credentials.getUsername())
                .filter(user -> passwordEncoder.matches(credentials.getPassword(),
                        user.getPassword()))
                .orElseThrow(() -> new BadLoginException());
    }

}
