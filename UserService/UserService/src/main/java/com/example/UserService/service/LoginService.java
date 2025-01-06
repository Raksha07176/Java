package com.example.UserService.service;

	import com.example.UserService.model.User;
	import com.example.UserService.repository.UserRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
	import org.springframework.stereotype.Service;

	@Service
	public class LoginService {

	    @Autowired
	    private UserRepository userRepository;  // Injecting the repository

	    
	    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	    // Register a new user
	    public String registerUser(String email, String password, String name, String address, long pincode, long contact) {
	        // Check if the email already exists in the database
	        if (userRepository.existsByEmail(email)) {
	            return "Email is already taken!";
	        }
			System.out.println("In loginService");
	        // Hash the password
	        String hashedPassword = passwordEncoder.encode(password);

	        // Create a new User object
	        User newUser = new User(name, email, hashedPassword, address, pincode, contact);
			System.out.println("In login service 2");
	        // Save the user to the database using the repository
	        userRepository.save(newUser);

	        return "User registration is successful";
	    }

	    // Authenticate a user
	    public String authenticateUser(String email, String password) {
	        // Find the user by email
	        Optional<User> userOptional = userRepository.findByEmail(email);

	        if (userOptional.isPresent()) {
	            User user = userOptional.get();
	            // Check if the password matches the stored hash
	            boolean isPasswordValid = passwordEncoder.matches(password, user.getPassword());
	            if (isPasswordValid) {
	            	System.out.println("Inside authenticateUser 1");
	            	return "Login Successful";
	            } else {
	            	System.out.println("Inside authenticateUser 2");
	                return "Invalid email or password!";
	            }
	        } else {
	            // If no user is found with the given email
	            return "User not found!";
	        }
	    }

		public String updateUser(int id, String email, String password, String name, String address, int pincode, int contact) {
			// Find the user by userId (assuming userId is unique)
			User existingUser = userRepository.findById(id).orElse(null);
			if (existingUser == null) {
				return "User not found.";
			}

			// Update the user details
			existingUser.setPassword(password);
			existingUser.setAddress(address);
			existingUser.setPincode(pincode);
			existingUser.setContact(contact);

			// Save the updated user
			userRepository.save(existingUser);
			return "User updated successfully.";
		}


		public String deleteUser(int id) {
			// Find the user by userId
			User existingUser = userRepository.findById(id).orElse(null);
			if (existingUser == null) {
				return "User not found.";
			}

			// Delete the user
			userRepository.delete(existingUser);
			return "User deleted successfully.";
		}
	}

