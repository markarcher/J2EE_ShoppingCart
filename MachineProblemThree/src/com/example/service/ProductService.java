package com.example.service;

import java.util.List;

import com.example.domain.InventoryItem;
import com.example.domain.Product;
import com.example.domain.ProductModel;

public interface ProductService {

	void addProduct(InventoryItem inventory,int categoryNum);

	List<Product> getProducts(int categoryType);

	
	Product getParticularProduct(String productName);

	void subtractQtyFrmProducts(List<InventoryItem> inventories);

	List<Product> getAllProducts();

	

}
