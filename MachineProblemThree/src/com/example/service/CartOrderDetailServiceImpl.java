package com.example.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.CartOrderDetailDao;
import com.example.dao.CartOrderDao;
import com.example.domain.InventoryItem;
import com.example.domain.ShoppingCart;

@Repository("cartOrderDetailDao")
public class CartOrderDetailServiceImpl implements CartOrderDetailService {
	
	@Autowired
	private CartOrderDetailDao cartOrderDetailDao;
	@Autowired
	private CartOrderDao cartOrderDao;
	
	
	@Transactional(readOnly = false)
	@Override
	public void addToOrderDetail(InventoryItem inventory, int orderId) {
		cartOrderDetailDao.addToOrderDetail(inventory,orderId);
		
	}
	
	@Transactional(readOnly = false)
	@Override
	public ShoppingCart getContentsInCart(int user,int orderId) {
		ShoppingCart shoppingcart;
		List<InventoryItem> inventories = new ArrayList<>();
		inventories = cartOrderDetailDao.getContentsInCart(user,orderId);
		if(inventories.isEmpty()){
			shoppingcart=null;
		}else{
			BigDecimal amount;
			amount = cartOrderDao.getTotalSales(user);
			shoppingcart = new ShoppingCart(inventories,"",amount);
		}
		return shoppingcart;
	}

	@Transactional(readOnly = false)
	@Override
	public void clearItemsFrmCart(int orderId) {
		cartOrderDetailDao.clearItemsFrmCart(orderId);
		
	}
	
	@Transactional(readOnly = false)
	@Override
	public void removeOneItem(int orderId, String productName) {
		cartOrderDetailDao.removeOneItem(orderId,productName);
		
	}

}
