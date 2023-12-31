package com.hygieia.app.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hygieia.app.Models.Availability;

@Repository
public interface AvailabilityRepository extends JpaRepository<Availability, Integer> {
    
    List<Availability> findAvailabiltyByEmployeeId(int id);
}
