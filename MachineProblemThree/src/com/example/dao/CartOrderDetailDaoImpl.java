package com.example.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.CartOrder;
import com.example.domain.CartOrderDetail;
import com.example.domain.InventoryItem;
import com.example.domain.Product;
import com.example.domain.ProductModel;

@Repository("cartOrderDetailDao")
public class CartOrderDetailDaoImpl implements CartOrderDetailDao {

	
	private SessionFactory sessionFactory;
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	@Transactional(readOnly = false)
	@Override
	public void addToOrderDetail(InventoryItem inventory,int orderId) {
		
		CartOrderDetail cartOrderDetail = new CartOrderDetail();
		cartOrderDetail.setOrderId(orderId);
		cartOrderDetail.setProductId(inventory.getProductModel().getProductId());
		cartOrderDetail.setUnitPrice(inventory.getProductModel().getPrice());
		cartOrderDetail.setQuantity(inventory.getQuantity());
		
		int productkey = inventory.getProductModel().getProductId();
		int quantity = inventory.getQuantity();
		Query q =  sessionFactory.getCurrentSession().createQuery("from CartOrderDetail cart where cart.productId = '"+productkey+"' AND cart.orderId = '"+orderId+"'");
		int productId = inventory.getProductModel().getProductId();
		CartOrderDetail  cart = null;
		
			if(q.list().isEmpty()){
				sessionFactory.getCurrentSession().saveOrUpdate(cartOrderDetail);
			}else{
				Session session = sessionFactory.getCurrentSession();
				cart = (CartOrderDetail) q.list().get(0);
				int qty = cart.getQuantity();
				int quantity1 = inventory.getQuantity();
				int additionalQty = qty+quantity1;
				cart.setQuantity(additionalQty);
				session.update(cart);
			}

	    
	}

	@Transactional(readOnly = false)
	@Override
	public List<InventoryItem> getContentsInCart(int user,int orderId) {
		Session session = sessionFactory.getCurrentSession();
		
		List<Product> products = (List<Product>) session.createQuery("select prod  from Product prod, CartOrderDetail cart where cart.productId = prod.productId AND cart.orderId =:orderid ").setParameter("orderid", orderId).list();
		
		List<CartOrderDetail> cart = session.createQuery("select cart  from  CartOrderDetail cart, Product prod  where cart.productId = prod.productId ").list();
		ProductModel productModel;
		InventoryItem inventory;
		List<InventoryItem> inventories = new ArrayList<>();
		for(Product p : products){
			
			productModel = new ProductModel(p.getProductId(),p.getProductName(),p.getPrice(),"");
			
				Query q = session.createQuery("select cart.quantity  from  CartOrderDetail cart where cart.productId = '"+p.getProductId()+"' AND cart.orderId =:orderid").setParameter("orderid", orderId);
			
				int quantity =  (int) q.list().get(0);
				inventory = new InventoryItem(productModel,quantity);
			    inventories.add(inventory);
		}
		
	return inventories;
	}
	
	@Transactional(readOnly = false)
	@Override
	public void checkOutItemsInOrderDetail(int orderId) {
		Session session = sessionFactory.getCurrentSession();
		Query q = session.createQuery("delete from CartOrderDetail cart where cart.orderId =:orderid").setParameter("orderid",orderId);
		q.executeUpdate();
				
	}

	@Transactional(readOnly = false)
	@Override
	public void clearItemsFrmCart(int orderId) {
		
		Session session = sessionFactory.getCurrentSession();
		Query q = session.createQuery("delete from CartOrder cart where cart.orderId =:orderid").setParameter("orderid",orderId);
		q.executeUpdate();
		
		Query qq = session.createQuery("delete from CartOrderDetail cart where cart.orderId =:orderid").setParameter("orderid",orderId);
		qq.executeUpdate();
		
	}
	
	@Transactional(readOnly = false)
	@Override
	public void removeOneItem(int orderId,String productName) {
		Session session = sessionFactory.getCurrentSession();
		Session session1 = sessionFactory.getCurrentSession();
		Query q = session1.createQuery("from CartOrder cart where cart.orderId =:orderid")
		.setParameter("orderid",orderId);
		CartOrder cartorder = (CartOrder) q.list().get(0);
		
		Query q1 = session.createQuery("from Product prod where prod.productName =:prodName")
				.setParameter("prodName",productName);
		Product product = (Product) q1.list().get(0);
		int prodId = product.getProductId();
		
		Query qq = session.createQuery("from CartOrderDetail cart where cart.orderId =:orderid AND cart.productId = '"+prodId+"' ").setParameter("orderid",orderId);
		CartOrderDetail cartOrderDetail = (CartOrderDetail) qq.list().get(0);
		BigDecimal price = cartOrderDetail.getUnitPrice();
		BigDecimal sales = cartorder.getTotalSales();
		sales = sales.subtract(price);
		cartorder.setTotalSales(sales);
		session1.update(cartorder);
		
		Query qqq = session.createQuery("delete from CartOrderDetail cart where cart.orderId =:orderid AND cart.productId = '"+prodId+"' ").setParameter("orderid",orderId);
		qqq.executeUpdate();
		
		
	}

}
