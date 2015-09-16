package com.example.dao;

import java.util.List;

import com.example.domain.InventoryItem;
import com.example.domain.Product;

public interface CartOrderDetailDao {

	void addToOrderDetail(InventoryItem inventory, int orderId);

	List<InventoryItem> getContentsInCart(int user, int orderId);

	void checkOutItemsInOrderDetail(int orderId);

	void clearItemsFrmCart(int orderId);

	void removeOneItem(int orderId, String productName);


}
