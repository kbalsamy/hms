package com.hygieia.app.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hygieia.app.Models.User;

public interface RoleRepository extends JpaRepository<User,Integer> 
{

   // Role findByname(String name);
    
}
