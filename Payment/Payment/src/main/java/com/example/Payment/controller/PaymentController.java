package com.example.Payment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Payment.model.PaymentInfo;
import com.example.Payment.service.PaymentService;

@RestController
@RequestMapping("/api")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;
	
	@PostMapping("/payment")
	public ResponseEntity<String> processPayment(@RequestBody PaymentInfo paymentInfo){
		
		String response = paymentService.processPayment(paymentInfo);
		return ResponseEntity.ok(response);
		
	}
	
	
}
