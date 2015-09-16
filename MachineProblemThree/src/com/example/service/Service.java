package com.example.service;

import java.util.List;

import com.example.domain.User;

public interface Service {

	void save(User user);

	List<User> getUserbyName(String userName);

}
