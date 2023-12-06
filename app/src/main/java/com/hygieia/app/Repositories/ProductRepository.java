package com.hygieia.app.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hygieia.app.Models.Product;

public interface ProductRepository extends JpaRepository<Product,Integer> {

    

    
}
