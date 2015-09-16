package com.example.domain;

import java.math.BigDecimal;
import java.util.List;

public class ShoppingCart {
	private List<InventoryItem> inventoryItem;
	private String user;
	private BigDecimal totalAmount;
	
	public ShoppingCart(List<InventoryItem> inventoryItem, String user,
			BigDecimal totalAmount) {
		super();
		this.inventoryItem = inventoryItem;
		this.user = user;
		this.totalAmount = totalAmount;
	}

	

	public List<InventoryItem> getInventoryItem() {
		return inventoryItem;
	}



	public void setInventoryItem(List<InventoryItem> inventoryItem) {
		this.inventoryItem = inventoryItem;
	}



	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	
	
	
	
}
