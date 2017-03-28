package com.niit.fairdeal.dao;

import java.util.List;

import com.niit.fairdeal.domain.Cart;

public interface CartDAO {
	
	public List<Cart> getAllCarts(int userID);
	
	public Cart get(int id);

	public boolean createCart(Cart cart);
	
	public boolean deleteCart(Cart cart); 
	
	public boolean updateCart(Cart cart);
	
	public Long getTotalAmount(int id);

	public int getAllCarts(String loggedInUserid);

	public Object getTotalAmount(String loggedInUserid);
}
