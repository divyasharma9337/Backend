package com.niit.shoppingcart.dao;

import java.util.List;

import com.niit.shoppingcart.domain.User;

//Data Access object-DAO 
public interface UserDAO {
	// declare the methods.
	// create new user
public boolean saveOrUpdate(User user);
	
	public User getUserByEmailId(String emailId);

	public List<User> getAllUsers();
	
	

}
