package com.hygieia.app.Repositories;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hygieia.app.Models.Department;



public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Boolean existsDepartmentByName(String name);

    Optional<Department> findDepartmentByName(String name);
    
} 