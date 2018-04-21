package com.niit.shoppingcart.dao;

import java.util.List;

import com.niit.shoppingcart.domain.Cart;

public interface CartDAO {
	public boolean saveOrUpdate(Cart cart);

	public Cart getCartById(int cartId);

	public boolean deleteCartById(int cartId);

	public List<Cart> getCartByUserId(String emailId);

	public Cart getCartItem(int productId, String emailId);

	public double getCartTotal(String emailId);

	public long getCartSize(String emailId);

	public int updateCartStatus(String emailId, String status);
}