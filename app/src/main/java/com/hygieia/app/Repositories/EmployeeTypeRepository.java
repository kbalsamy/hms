package com.hygieia.app.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hygieia.app.Models.EmployeeType;

@Repository
public interface EmployeeTypeRepository extends JpaRepository<EmployeeType,Integer> {

    
    Boolean existsByEmpTypeName(String empTypeName);

    EmployeeType findByEmpTypeName(String name);
    
}
