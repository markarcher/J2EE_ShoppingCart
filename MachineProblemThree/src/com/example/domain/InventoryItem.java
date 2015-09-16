package com.example.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


public class InventoryItem {
	private ProductModel productModel;
	private int quantity;
	
	
	
	
	public InventoryItem() {
		
	}

	public InventoryItem(ProductModel productModel, int quantity) {
		super();
		this.productModel = productModel;
		this.quantity = quantity;
		
	}

	public ProductModel getProductModel() {
		return productModel;
	}

	public void setProductModel(ProductModel productModel) {
		this.productModel = productModel;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	
	
}
