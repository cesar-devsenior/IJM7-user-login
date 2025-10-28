package com.devsenior.cdiaz.userlogin.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.devsenior.cdiaz.userlogin.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var userInfo = userRepository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario no existe"));

        var response = User.builder()
                .username(username)
                .password(userInfo.getPassword())
                .roles(userInfo.getRole().toString()) // ROLE_ADMIN, ROLE_USER
                //.authorities("EDITOR", "PUBLISHER")
                .build();

        return response;
    }

}
