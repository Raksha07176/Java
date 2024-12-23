package com.example.Payment.model;

public class PaymentInfo {
	
	private String userId;
	private String name;
	private String email;
	private String prodId;
	private String prodName;
	private int prodQuantity;
	private double price;
	
	public PaymentInfo(String userId, String name, String email, String prodId, String prodName, int prodQuantity,
			double price) {
		super();
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.prodId = prodId;
		this.prodName = prodName;
		this.prodQuantity = prodQuantity;
		this.price = price;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getProdId() {
		return prodId;
	}
	public void setProdId(String prodId) {
		this.prodId = prodId;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public int getProdQuantity() {
		return prodQuantity;
	}
	public void setProdQuantity(int prodQuantity) {
		this.prodQuantity = prodQuantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	
	
}
