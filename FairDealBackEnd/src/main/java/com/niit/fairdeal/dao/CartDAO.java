package com.niit.fairdeal.dao;

import java.util.List;

import com.niit.fairdeal.domain.Cart;

public interface CartDAO {
	
	public List<Cart> getAllCarts(String userID);
	
	public Cart get(String id);

	public boolean saveCart(Cart cart);
	
	public boolean deleteCart(Cart cart); 
	
	public boolean updateCart(Cart cart);
	
	public Long getTotalAmount(String id);
}
