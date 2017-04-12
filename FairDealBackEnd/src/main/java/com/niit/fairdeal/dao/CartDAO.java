package com.niit.fairdeal.dao;

import java.util.List;

import com.niit.fairdeal.domain.Cart;

public interface CartDAO {
	public List<Cart> list();

	public boolean save(Cart cart);

	public boolean update(Cart cart);

	public boolean delete(Cart cart);

	public List<Cart> get(int userid);
	
	public Cart getitem(int cartId);

	public Cart getproduct(int id,int userid);
	
	//public Cart getproduct(int cartid);

	public long cartsize(int userId);

	public long CartPrice(int userId);

	public void pay(int userId);
}