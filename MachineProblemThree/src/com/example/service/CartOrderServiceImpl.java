package com.example.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.CartOrderDetailDao;
import com.example.dao.CartOrderDao;
import com.example.dao.ProductDao;
import com.example.domain.CartOrder;

@Repository("cartOrderDao")
public class CartOrderServiceImpl implements CartOrderService {
	@Autowired
	private CartOrderDao cartOrderDao;
	@Autowired
	private CartOrderDetailDao cartOrderDetailDao;
	
	@Transactional(readOnly = false)
	@Override
	public void addToOrder(CartOrder cartOrder) {
		cartOrderDao.addToOrder(cartOrder);
		
	}
	@Transactional(readOnly = false)
	@Override
	public int getOrderNumber(int user) {
		int orderId = cartOrderDao.getOrderNumber(user);
		
	  return orderId;
	}
	
	@Transactional(readOnly = false)
	@Override
	public void increaseTotalSales(BigDecimal totalSales, int user) {
		 cartOrderDao.increaseTotalSales(totalSales,user);
		
	}
	
	@Transactional(readOnly = false)
	@Override
	public BigDecimal getTotalSales(int user) {
		BigDecimal amount;
		amount = cartOrderDao.getTotalSales(user);
		return amount;
	}
	
	@Transactional(readOnly = false)
	@Override
	public void checkOutItems(int orderId) {
		cartOrderDao.checkoutItems(orderId);
		cartOrderDetailDao.checkOutItemsInOrderDetail(orderId);
		
	}

}
