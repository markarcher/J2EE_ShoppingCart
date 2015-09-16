package com.example.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.User;

@Repository("useDao")
public class UserDaoImpl implements UserDao {
	
	
	
	private SessionFactory sessionFactory;
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	@Transactional(readOnly = false)
	@Override
	public void save(User user) {
	
		sessionFactory.getCurrentSession().saveOrUpdate(user);
		
	}
	@Transactional(readOnly = false)
	@Override
	public List<User> getUserbyName(String userName) {
		Session session = sessionFactory.getCurrentSession();
		return (List<User>) session.createQuery("from User").list();
	
	}

}
