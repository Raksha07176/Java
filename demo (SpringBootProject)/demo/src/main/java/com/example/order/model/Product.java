package com.example.order.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Product {

	@Id
	private long orderId;
	private int userId;
	private String prodName;
	private long prodPrice;

	
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public Product(String prodName, long orderId, long prodPrice) {
		super();
		this.prodName = prodName;
		this.orderId = orderId;
		this.prodPrice = prodPrice;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public long getProdId() {
		return orderId;
	}
	public void setProdId(long orderId) {
		this.orderId = orderId;
	}

	public long getProdPrice() {
		return prodPrice;
	}
	public void setProdPrice(long prodPrice) {
		this.prodPrice = prodPrice;
	}



}
