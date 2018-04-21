package com.niit.shoppingcart.dao;

import java.util.List;

import com.niit.shoppingcart.domain.Orders;

public interface OrderDAO {

	public boolean saveOrUpdate(Orders order);
	
	public Orders getOrderById(int orderId);
	
	public boolean deleteOrderById(int orderId);
	
	public List<Orders> getAllOrdersOfUser(String emailId);
	
	
}
