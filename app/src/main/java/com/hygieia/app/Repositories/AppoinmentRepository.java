package com.hygieia.app.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hygieia.app.Models.Appointment;

@Repository
public interface AppoinmentRepository extends JpaRepository<Appointment, Integer> {

    
} 
