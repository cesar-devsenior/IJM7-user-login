package com.devsenior.cdiaz.userlogin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.devsenior.cdiaz.userlogin.mapper.UserMapper;

@Configuration
public class BeanConfiguration {
    
    @Bean
    UserMapper userMapper(PasswordEncoder passwordEncoder) {
        return new UserMapper(passwordEncoder);
    }

}
