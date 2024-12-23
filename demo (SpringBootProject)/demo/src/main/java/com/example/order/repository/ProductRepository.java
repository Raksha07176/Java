package com.example.order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.order.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	List<Product> findByUserId(Long userId);
	
}
