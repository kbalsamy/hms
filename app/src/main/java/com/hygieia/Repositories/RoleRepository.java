package com.hygieia.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hygieia.Entities.Role;
import com.hygieia.Entities.User;

public interface RoleRepository extends JpaRepository<User,Integer> 
{

    Role findByname(String name);
    
}
