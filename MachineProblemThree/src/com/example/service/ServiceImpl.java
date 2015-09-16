package com.example.service;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.UserDao;
import com.example.dao.UserDaoImpl;
import com.example.domain.User;

@Repository("userDao")
public class ServiceImpl implements Service {
	@Autowired
	private UserDao userDao;
	
	
	@Transactional(readOnly = false)
	@Override
	public void save(User user) {
	
		userDao.save(user);
		
	}

	@Transactional(readOnly = false)
	@Override
	public List<User> getUserbyName(String userName) {
		List<User> users = new ArrayList<>();
		users = userDao.getUserbyName(userName);
		return users;
	}

}
