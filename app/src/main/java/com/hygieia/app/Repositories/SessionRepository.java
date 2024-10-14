package com.hygieia.app.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hygieia.app.Models.SessionData;

public interface SessionRepository extends JpaRepository<SessionData,String> {

    Optional<SessionData> findByHostUsername(String hostUsername);
}

    

