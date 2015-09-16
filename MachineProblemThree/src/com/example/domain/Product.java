package com.example.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="tbl_product")
@Entity
public class Product {
	private int productId;
	private String productName;
	private BigDecimal price;
	private int category;
	private int quantity;

	public Product() {
		
	}

	public Product(int productId,String productName,BigDecimal price, int category) {
		super();
		this.productName = productName;
		this.price = price;
		this.category = category;
		this.productId = productId;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="key_product")
	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	@Column(name="fld_product_name")
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	@Column(name="fld_unit_price")
	public BigDecimal getPrice() {
		return price;
	}
	
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	@Column(name="fld_inventory_qty")
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Column(name="key_category")
	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}
	
	
	
}
