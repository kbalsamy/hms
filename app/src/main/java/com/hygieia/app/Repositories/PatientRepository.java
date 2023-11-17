package com.hygieia.app.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hygieia.app.Models.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Integer> {

    Boolean existsByUserName(String username);
    
}
