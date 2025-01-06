package com.example.UserService.controller;

import com.example.UserService.model.User;
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
		System.out.println("Calling register html page");
		return "register";
	}

	// Register User endpoint
	@PostMapping("/register")
	public String registerUser(@RequestParam String name, @RequestParam String email, @RequestParam String password,
			@RequestParam String address, @RequestParam Long pincode, @RequestParam Long contact, Model model) {

		try {
			// Directly use pincode and contact as Long
			System.out.println("In register user");

			// Call the service to register the user
			String registrationResult = loginService.registerUser(email, password, name, address, pincode, contact);

			// Add the result message to the model
			model.addAttribute("message", registrationResult);
			return "login"; // Redirect to login page after successful registration

		} catch (Exception e) {
			System.out.println("Error during registration: " + e.getMessage());
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
	public String login(@RequestParam String email, @RequestParam String password, Model model) {

		// Call the service to authenticate the user
		String authenticationResult = loginService.authenticateUser(email, password);
		System.out.println(authenticationResult);

		// If authentication is successful, fetch product data using WebClient
		if (authenticationResult.startsWith("Login Successful")) {
			System.out.println("Inside login controller 1");



			// Return a successful response with the product data
			return "redirect:http://localhost:8091/api/orders/products"; // 200 OK with the product data
		} else {
			// If authentication fails, return to login page with error message
			System.out.println("Inside login controller 2");

			// Return error response with 401 Unauthorized status
			return "login";
		}
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<String> updateUser(
			@PathVariable("id") int id,
			@RequestBody User updatedUser) {

		try {
			// Ensure the provided pincode and contact are valid integers
			int pincodeInt = (int) updatedUser.getPincode();
			int contactInt = (int) updatedUser.getContact();

			// Call the service to update the user details
			String updateResult = loginService.updateUser(id, updatedUser.getEmail(), updatedUser.getPassword(),
					updatedUser.getName(), updatedUser.getAddress(), pincodeInt, contactInt);

			// Handle the result from the service
			if (updateResult.equals("User updated successfully")) {
				return new ResponseEntity<>(updateResult, HttpStatus.OK); // 200 OK
			} else {
				return new ResponseEntity<>(updateResult, HttpStatus.BAD_REQUEST); // 400 Bad Request
			}
		} catch (NumberFormatException e) {
			return new ResponseEntity<>("Invalid input for pincode or contact.", HttpStatus.BAD_REQUEST);
		}
	}


	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable("id") int id) {
		// Call the service to delete the user
		String deletionResult = loginService.deleteUser(id);

		if (deletionResult.equals("User deleted successfully")) {
			return new ResponseEntity<>(deletionResult, HttpStatus.OK); // 200 OK
		} else {
			return new ResponseEntity<>(deletionResult, HttpStatus.NOT_FOUND); // 404 Not Found
		}
	}
}
