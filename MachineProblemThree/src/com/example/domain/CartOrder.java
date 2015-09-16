package com.example.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="tbl_order")
@Entity
public class CartOrder {
	
	private int orderId;
	private int userId;
	private BigDecimal totalSales;
	public CartOrder() {
		
		super();
		
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="key_order")
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	
	@Column(name="key_user", unique=true)
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	@Column(name="fld_amount")
	public BigDecimal getTotalSales() {
		return totalSales;
	}
	public void setTotalSales(BigDecimal totalSales) {
		this.totalSales = totalSales;
	}
	
		
}
