package com.hygieia.Repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hygieia.Entities.User;

public interface UserRepository  extends JpaRepository<User,Integer>{

    @Query(value = "select * from users u where u.email=:email", nativeQuery=true)
    User findByEmail(@Param("email") String email);

    
} 
