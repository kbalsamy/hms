package com.hygieia.app.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hygieia.app.Repositories.AuthUserRepository;

@Service
public class PasswordService {

@Autowired
    PasswordEncoder passwordEncoder;


    public String EncodePassword(String password){

        return passwordEncoder.encode(password);


    }


    
}
