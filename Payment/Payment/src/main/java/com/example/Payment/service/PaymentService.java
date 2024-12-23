package com.example.Payment.service;

import org.springframework.stereotype.Service;

import com.example.Payment.model.PaymentInfo;

@Service
public class PaymentService {
	
	public String processPayment(PaymentInfo paymentInfo) {
		if(paymentInfo == null) {
			return "Payment Info is missing";
		}
		
		if(paymentInfo.getPrice() <= 0) {
			return "Invalid payment amount";
		}
		
		if("fail".equalsIgnoreCase(paymentInfo.getUserId())) {
			return "Payment failed ! Please try again.";
		}
		
		double totalAmount = paymentInfo.getPrice() * paymentInfo.getProdQuantity();
		
		return "Payment of rupees" + totalAmount + "processed successfully for user " + paymentInfo.getName() + 
				"(Product: " + paymentInfo.getProdName() + " x " + paymentInfo.getProdQuantity() + ")";
		
	}

}
