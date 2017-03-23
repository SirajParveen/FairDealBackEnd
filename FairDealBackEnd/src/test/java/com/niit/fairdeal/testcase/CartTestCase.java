package com.niit.fairdeal.testcase;

import org.junit.BeforeClass;
import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.fairdeal.dao.CartDAO;
import com.niit.fairdeal.domain.Cart;

public class CartTestCase 
{
	@Autowired
	private static CartDAO cartDAO;

	@Autowired
	private static Cart cart;

	@BeforeClass
	public static void initialize() 
	{
		@SuppressWarnings("resource")
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();

		cart = (Cart) context.getBean("cart");
		cartDAO = (CartDAO) context.getBean("cartDAO");

	}
	
	/*@Test
	public void getAllCartsTestCase(String userID)
	{
		int recordsFromDB = cartDAO.getAllCarts("01").size();
		assertEquals("getAllCartsTestCase", 12, recordsFromDB);
	}
	*/
	/*@Test
	public void saveCartTestCase()
	{
		cart.setId(100L);
		cart.setPrice(2000);
		cart.setProductName("samsung j2");
		cart.setQuantity(1);
		cart.setStatus('N');
		cart.setUserID("04");
		
		boolean flag = cartDAO.saveCart(cart);
		assertEquals("saveCartTestCase", true, flag);
	}
	*/
	/*@Test
	public void getTotalAmountTestCase()
	{
		Long flag = cartDAO.getTotalAmount("04");
		assertEquals("getTotalAmountTestCase", null, flag);	
	}*/
	/*
	@Test
	public Cart get(String id)
	{
		
	}
	*/
	@Test
	public void deleteCartTestCase()
	{
		cart.setId(5);
		boolean flag = cartDAO.deleteCart(cart);
		assertEquals("deleteCartTestCase", true, flag);
	}
	/*
	@Test
	public boolean updateCart(Cart cart)
	{
		
	}*/
}
