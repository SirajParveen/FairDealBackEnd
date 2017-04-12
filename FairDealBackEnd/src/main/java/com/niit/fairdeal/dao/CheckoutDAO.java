package com.niit.fairdeal.dao;

import java.util.List;

import com.niit.fairdeal.domain.Checkout;

public interface CheckoutDAO {
	public boolean saveOrUpdate(Checkout checkout);

	public boolean delete(Checkout checkout);

	public List<Checkout> list();

	public Checkout get(int id);
}
