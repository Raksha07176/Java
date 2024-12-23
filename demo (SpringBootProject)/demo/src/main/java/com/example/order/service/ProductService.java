package com.example.order.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.order.model.Product;
import com.example.order.repository.ProductRepository;

@Service
public class ProductService {

	
	private ProductRepository productRepository;
	
	/*public Product createOrder(int userId, String prodName) {
	    // Create OrderEntity directly without using OrderDTO
	    Product product = Product.builder()
	            .userId(userId)
	            .prodName(prodName)
	            .build();
	    
	    // Save the OrderEntity and return the saved entity
	    System.out.println(userId);
	    System.out.println(product);
	    
	    product = productRepository.save(product);
	    return product;  // Return the OrderEntity instead of OrderDTO
	}

	public List<Product> getOrdersByUserId(Long userId) {
	    // Fetch list of orders as OrderEntity objects
	    List<Product> orders = productRepository.findByUserId(userId);
	    return orders;  // Return the list of OrderEntity objects directly
	}*/

	
}
