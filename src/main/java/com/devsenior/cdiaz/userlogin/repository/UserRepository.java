package com.devsenior.cdiaz.userlogin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsenior.cdiaz.userlogin.model.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

}
