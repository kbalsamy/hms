package com.hygieia.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hygieia.Entities.User;

public interface UserRepository  extends JpaRepository<User,Integer>{

    User findByEmain(String email);

    
} 
