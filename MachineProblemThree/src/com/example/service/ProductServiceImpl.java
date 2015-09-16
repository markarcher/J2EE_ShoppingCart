package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.ProductDao;
import com.example.domain.InventoryItem;
import com.example.domain.Product;
import com.example.domain.ProductModel;

@Repository("productdao")
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductDao productdao;
	
	@Transactional(readOnly = false)
	@Override
	public void addProduct(InventoryItem inventory, int categoryNum) {
		productdao.addProduct(inventory,categoryNum);
		
	}
	
	@Transactional(readOnly = false)
	@Override
	public List<Product> getProducts(int categoryType) {
		List<Product> products = productdao.getProducts(categoryType);
		return products;
	}

	
	@Transactional(readOnly = false)
	@Override
	public Product getParticularProduct(String productName) {
		Product prod =	productdao.getParticularProduct(productName);
		return prod;
	}
	
	@Transactional(readOnly = false)
	@Override
	public void subtractQtyFrmProducts(List<InventoryItem> inventories) {
		productdao.subtractQtyFrmProduct(inventories);
		
	}

	@Transactional(readOnly = false)
	@Override
	public List<Product> getAllProducts() {
		List<Product> products = productdao.getAllProducts();
		return products;
	}

}
