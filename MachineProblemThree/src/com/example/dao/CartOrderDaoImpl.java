package com.example.dao;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.CartOrder;
import com.example.domain.Product;

@Repository("cartOrderDao")
public class CartOrderDaoImpl implements CartOrderDao {
	
	
	private SessionFactory sessionFactory;
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	
	@Transactional(readOnly = false)
	@Override
	public void addToOrder(CartOrder cartOrder) {
		
		sessionFactory.getCurrentSession().saveOrUpdate(cartOrder);

	}

	@Transactional(readOnly = false)
	@Override
	public int getOrderNumber(int user) {
		Session session = sessionFactory.getCurrentSession();
		List<CartOrder> cartorder = (List<CartOrder>) session
				.createQuery("from CartOrder cart where cart.userId =:userId")
				.setParameter("userId",user).list();
		
		int orderId = 0;
		for(CartOrder co : cartorder){
			if(co.getUserId()==user){
				orderId =  co.getOrderId();
			}
		}
	    return orderId;
	}

	@Transactional(readOnly = false)
	@Override
	public void increaseTotalSales(BigDecimal totalSales,int user) {
		Session session = sessionFactory.getCurrentSession();
		Query q = session.createQuery("from CartOrder cart where cart.userId =:userId")
		.setParameter("userId",user);
		CartOrder cartorder = (CartOrder) q.list().get(0);
		
		BigDecimal sales = cartorder.getTotalSales();
		totalSales = totalSales.add(sales);
		cartorder.setTotalSales(totalSales);
		session.update(cartorder);
		
	}


	@Transactional(readOnly = false)
	@Override
	public BigDecimal getTotalSales(int user) {
		Session session = sessionFactory.getCurrentSession();
		Query q = session.createQuery("from CartOrder cart where cart.userId =:userId")
				.setParameter("userId",user);
				CartOrder cartorder = (CartOrder) q.list().get(0);
		return 	cartorder.getTotalSales();
		
	}

	@Transactional(readOnly = false)
	@Override
	public void checkoutItems(int orderId) {
		Session session = sessionFactory.getCurrentSession();
		Query q = session.createQuery("from CartOrder cart where cart.orderId =:orderid").setParameter("orderid",orderId);
		CartOrder cartorder = (CartOrder) q.list().get(0);
		session.delete(cartorder);
	}
    
}
