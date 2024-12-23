package com.example.order.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.order.model.Product;
import com.example.order.service.ProductService;

import ch.qos.logback.core.model.Model;

@Controller
@RequestMapping("/api/orders")
public class ProductController {	
	
	@GetMapping("/products")
	public String showProducts() {
		System.out.println("In products");
		return "I love Ankur";
	}
	
	@GetMapping("/place_order")
	public String showOrderForm(@RequestParam String ProdName, Model model) {
		return "order-creation";
	}
	
	/*@PostMapping("/create")
	public String createOrder(@RequestParam int userId, @RequestParam String prodName, Model model) {
	    // Directly create the Order entity without using OrderDTO
	    Product product = ProductService.createOrder(userId, prodName);
	    
	    // Check if the order's userId matches the provided userId
	    if (product.getUserId().equals(userId)) {
	        return "product";  // You can return the same template if needed
	    } else {
	        model.addAttribute("order", product);  // Add order to model
	        return "product";  // Thymeleaf will look for a template named "Order Created.html"
	    }
	}*/

	
}
