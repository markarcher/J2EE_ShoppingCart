package com.example.domain;

import java.math.BigDecimal;

public class ProductModel {
	private int productId;
	private String productName;
	private BigDecimal price;
	private String categoryName;
	
	public ProductModel(int productId,String productName,BigDecimal price, String categoryName) {
		super();
		this.productName = productName;
		this.price = price;
		this.categoryName = categoryName;
		this.productId = productId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	
	
}
