package com.example.service;

import java.math.BigDecimal;

import com.example.domain.CartOrder;


public interface CartOrderService {

	void addToOrder(CartOrder cartOrder);

	int getOrderNumber(int user);

	void increaseTotalSales(BigDecimal totalSales, int user);

	BigDecimal getTotalSales(int user);

	void checkOutItems(int orderId);

}
