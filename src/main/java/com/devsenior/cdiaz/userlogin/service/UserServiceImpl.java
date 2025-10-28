package com.devsenior.cdiaz.userlogin.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.devsenior.cdiaz.userlogin.model.dto.UserRequest;
import com.devsenior.cdiaz.userlogin.model.dto.UserResponse;
import com.devsenior.cdiaz.userlogin.model.entity.User;
import com.devsenior.cdiaz.userlogin.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<UserResponse> getAll() {
        return userRepository.findAll().stream()
                .map(u -> UserResponse.builder()
                        .username(u.getUsername())
                        .hireDate(u.getHireDate())
                        .active(u.getActive())
                        .email(u.getEmail())
                        .role(u.getRole())
                        .name(u.getName())
                        .build())
                .toList();
    }

    @Override
    public UserResponse create(UserRequest entity) {
        // TODO: Validar la informacion que llega

        var user = User.builder()
                .username(entity.getUsername())
                .hireDate(LocalDate.now())
                .active(true)
                .email(entity.getEmail())
                .role(entity.getRole())
                .name(entity.getFullName())
                .build();

        user = userRepository.save(user);

        return UserResponse.builder()
                .username(user.getUsername())
                .hireDate(user.getHireDate())
                .active(user.getActive())
                .email(user.getEmail())
                .role(user.getRole())
                .name(user.getName())
                .build();
    }
}
