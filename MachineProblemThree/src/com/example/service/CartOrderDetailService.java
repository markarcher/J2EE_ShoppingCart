package com.example.service;

import java.util.List;

import com.example.domain.InventoryItem;
import com.example.domain.ShoppingCart;

public interface CartOrderDetailService {

	void addToOrderDetail(InventoryItem inventory, int orderId);

	ShoppingCart getContentsInCart(int user, int orderId);

	void clearItemsFrmCart(int orderId);

	void removeOneItem(int orderId, String productName);

	

}
