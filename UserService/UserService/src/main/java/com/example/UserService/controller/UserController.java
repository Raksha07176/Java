package com.example.UserService.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import com.example.UserService.service.LoginService;

@Controller
@RequestMapping("/api")
public class UserController {

	@Autowired
	private LoginService loginService;
	 @Autowired
	    private WebClient.Builder webClientBuilder;

	// Show registration form
	@GetMapping("/register")
	public String showRegisterForm() {
		return "register";
	}

	// Register User endpoint
	@PostMapping("/register")
	public String registerUser(@RequestParam String name, @RequestParam String email, @RequestParam String password,
			@RequestParam String address, @RequestParam String pincode, @RequestParam String contact, Model model) {
		try {
			// Convert pincode and contact to integers
			int pincodeInt = Integer.parseInt(pincode);
			int contactInt = Integer.parseInt(contact);

			// Call the service to register the user
			String registrationResult = loginService.registerUser(email, password, name, address, pincodeInt,
					contactInt);

			// Add the result message to the model
			model.addAttribute("message", registrationResult);
			return "login"; // Redirect to login page after successful registration

		} catch (NumberFormatException e) {
			model.addAttribute("error", "Invalid input for pincode or contact.");
			return "register"; // Return to register page if input is invalid
		}
	}

	// Show login page
	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}

	// Login User endpoint
	@PostMapping("/login")
	@ResponseBody
	public ResponseEntity<String> login(@RequestParam String email, @RequestParam String password, Model model) {

		// Call the service to authenticate the user
		String authenticationResult = loginService.authenticateUser(email, password);
		System.out.println(authenticationResult);

		// If authentication is successful, fetch product data using WebClient
		if (authenticationResult.startsWith("Login Successful")) {
			System.out.println("Inside login controller 1");

			// Fetching product data using WebClient
			String productResponse = webClientBuilder.baseUrl("http://localhost:8091")
					.build()
					.get()
					.uri("/api/orders/products")
					.retrieve()
					.bodyToMono(String.class)
					.block();  // This makes the call synchronous

			System.out.println("Product response: " + productResponse);

			// Return a successful response with the product data
			return ResponseEntity.ok(productResponse); // 200 OK with the product data
		} else {
			// If authentication fails, return to login page with error message
			System.out.println("Inside login controller 2");

			// Return error response with 401 Unauthorized status
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(authenticationResult);
		}
	}
}
