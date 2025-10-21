package com.devsenior.cdiaz.userlogin.service;

import com.devsenior.cdiaz.userlogin.model.dto.LoginRequest;
import com.devsenior.cdiaz.userlogin.model.dto.RegisterRequest;
import com.devsenior.cdiaz.userlogin.model.dto.RegisterResponse;

public interface AuthenticationService {
    RegisterResponse register(RegisterRequest userInfo);

    void login(LoginRequest body);
}
