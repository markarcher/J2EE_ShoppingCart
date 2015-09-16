package com.example.dao;

import java.util.List;

import com.example.domain.User;

public interface UserDao {

	void save(User user);

	List<User> getUserbyName(String userName);

}
