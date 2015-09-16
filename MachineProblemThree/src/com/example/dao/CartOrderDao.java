package com.example.dao;

import java.math.BigDecimal;

import com.example.domain.CartOrder;

public interface CartOrderDao {

	void addToOrder(CartOrder cartOrder);

	int getOrderNumber(int user);

	void increaseTotalSales(BigDecimal totalSales, int user);

	BigDecimal getTotalSales(int user);

	void checkoutItems(int orderId);

}
