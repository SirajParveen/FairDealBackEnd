package com.niit.shoppingcart.testcase;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.shoppingcart.dao.UserDAO;
import com.niit.shoppingcart.domain.User;

public class UserTestCase {

	@Autowired
	private static User user;
	
	@Autowired
	private static UserDAO userDAO;
	
	@BeforeClass
	public static void initialize()
	{
		@SuppressWarnings("resource")
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		
		context.scan("com.niit");
		context.refresh();
		
		user = (User) context.getBean("user");
		userDAO = (UserDAO) context.getBean("userDAO");
	}
	
	@Test
	public void getAllUsersTestCase()
	{
		int recordFromDB = userDAO.getAllUsers().size();
		
		assertEquals("getAllUsersTestCase", 5, recordFromDB);
	}
	
	@Test
	public void createUserTestCase()
	{
		user.setID("04");
		user.setName("Pooja");
		user.setPassword("pooja");
		user.setContact("76543567879");
		user.setRole("user");
		
		boolean flag = userDAO.createUser(user);
		
		assertEquals("createUserTestCase", true, flag);
	}
	
	@Test
	public void updateUserTestCase()
	{
		user.setID("03");
		user.setName("Akhil");
		user.setPassword("akhil");
		user.setContact("8765432467");
		user.setRole("user");
		
		boolean flag = userDAO.updateUser(user);
		
		assertEquals("updateUserTestCase", true, flag);
	}
	
	@Test
	public void deleteUserTestCase()
	{
		user.setPassword("siraj");
		
		boolean flag = userDAO.deleteUser(user);
		
		assertEquals("deleteUserTestCase", true, flag);
	}
	
	@Test
	public void getUserByIDTestCase()
	{
		user = userDAO.getUserByID("01");
		
		assertEquals("getUserByIDTestCase", null, user);
	}
	
	@Test
	public void getUserByNameTestCase()
	{
		user = userDAO.getUserByName("Siraj");
		
		assertEquals("getUserByNameTestCase", null, user);
	}
	
	@Test
	public void validateUserTestCase()
	{
		boolean flag = userDAO.validateUser("01", "siraj");
		
		assertEquals("validateUserTestCase", true, flag);
	}
}
