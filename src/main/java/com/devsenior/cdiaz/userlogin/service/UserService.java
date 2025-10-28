package com.devsenior.cdiaz.userlogin.service;

import java.util.List;

import com.devsenior.cdiaz.userlogin.model.dto.UserRequest;
import com.devsenior.cdiaz.userlogin.model.dto.UserResponse;

public interface UserService {
    
    List<UserResponse> getAll();

    UserResponse create(UserRequest entity);
}
