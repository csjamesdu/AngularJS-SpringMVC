package com.csjamesdu.springmvc.dao;

import java.util.List;

import com.csjamesdu.springmvc.model.User;

public interface UserDao {

	List<User> list();

	User loadById(Long id);

	void update(User user);

	void delete(User user);

	void add(User user);
	
	
}
