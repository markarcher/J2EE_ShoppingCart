package com.example.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Table(name="tbl_order_detail")
@Entity
//, uniqueConstraints = @UniqueConstraint(columnNames = { "key_order","key_product" })
public class CartOrderDetail {
	
	private int orderDetailId;
	private int orderId;
	private int productId;
	private BigDecimal unitPrice;
	private int quantity;
	
	public CartOrderDetail() {

	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="key_order_detail")
	public int getOrderDetailId() {
		return orderDetailId;
	}

	public void setOrderDetailId(int orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	@Column(name="key_order")
	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	
	@Column(name="key_product")
	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	@Column(name="fld_unit_price")
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	@Column(name="fld_quantity")
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	

	
	
}
