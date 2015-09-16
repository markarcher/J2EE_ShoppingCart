package com.example.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Category;
import com.example.domain.InventoryItem;
import com.example.domain.Product;
import com.example.domain.ProductModel;
@Repository("productdao")
public class ProductDaoImpl implements ProductDao {

	private SessionFactory sessionFactory;
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	@Transactional(readOnly = false)
	@Override
	public void addProduct(InventoryItem inventory, int categoryNum) {
		
		Product product = new Product();
		product.setProductName(inventory.getProductModel().getProductName());
		product.setQuantity(inventory.getQuantity());
		product.setPrice(inventory.getProductModel().getPrice());
		product.setCategory(categoryNum);
		sessionFactory.getCurrentSession().saveOrUpdate(product);
	}
	
	@Transactional(readOnly = false)
	@Override
	public List<Product> getProducts(int categoryType) {
	
		Session session = sessionFactory.getCurrentSession();
		return (List<Product>) session.createQuery("from Product prod where prod.category =:prodcategory").setParameter("prodcategory",categoryType).list();
	}

	@Transactional(readOnly = false)
	@Override
	public Product getParticularProduct(String productName) {
		
		Session session = sessionFactory.getCurrentSession();
		Product product = (Product) session
				.createQuery("from Product prod where prod.productName =:productName")
				.setParameter("productName", productName)
				.list().get(0);
		return product;
	}

	@Transactional(readOnly = false)
	@Override
	public void subtractQtyFrmProduct(List<InventoryItem> inventories) {
		
		Session session = sessionFactory.getCurrentSession();
		for(InventoryItem i : inventories){
			String productName = i.getProductModel().getProductName();
			Query q = session.createQuery("from Product prod where prod.productName =:prodName").setParameter("prodName", productName);
		     Product product = (Product) q.list().get(0);
		     int quantity = product.getQuantity();
		     quantity = quantity - i.getQuantity();
		     product.setQuantity(quantity);
		     session.update(product);
		     
		}
	}

	@Override
	public List<Product> getAllProducts() {
		Session session = sessionFactory.getCurrentSession();
		return (List<Product>) session.createQuery("from Product").list();
	
	}

	

}
