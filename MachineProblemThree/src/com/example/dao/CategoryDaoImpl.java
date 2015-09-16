package com.example.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Category;

@Repository("categorydao")
public class CategoryDaoImpl implements CategoryDao {

	private SessionFactory sessionFactory;
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	@Transactional(readOnly = false)
	@Override
	public void saveCategory(Category category) {
		sessionFactory.getCurrentSession().saveOrUpdate(category);
		
	}
	
	@Transactional(readOnly = false)
	@Override
	public List<Category> getAllCategories() {
		Session session = sessionFactory.getCurrentSession();
		return (List<Category>) session.createQuery("from Category").list();
		
	}
	
	@Transactional(readOnly = false)
	@Override
	public Category getCategoryId(String categoryName) {
		
		
		Session session = sessionFactory.getCurrentSession();
		List<Category> categories = (List<Category>) session.createQuery("from Category").list();
		Category category = null;
		for(Category c : categories){
			
			if(c.getCategoryName().equals(categoryName)){
			    category = new Category(c.getCategoryNo(),c.getCategoryName());
			}
		}
	
	return category;
	}

	
	
	

}
