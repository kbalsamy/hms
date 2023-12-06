package com.hygieia.app.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hygieia.app.Models.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    
} 