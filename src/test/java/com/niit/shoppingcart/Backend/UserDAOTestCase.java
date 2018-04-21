/*package com.niit.shoppingcart.Backend;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.shoppingcart.dao.UserDAO;
import com.niit.shoppingcart.domain.User;

public class UserDAOTestCase {
	
	private static AnnotationConfigApplicationContext context;
	@Autowired
	private static UserDAO userDAO;
	@Autowired
	private static User user;
	@BeforeClass
	public static void init() {
		context=new AnnotationConfigApplicationContext();
		context.scan("com.niit.shoppingcart"); //scan the complete packages and check for annotations like
		//@component,@controller,
		//clear the context bean factory,and recreate all the instances of the classes which are there 
		//in com.niit.shopping cart
		//with proper annotation
		context.refresh();
		 userDAO=(UserDAO)context.getBean("userDAO");
		 user=(User)context.getBean("user");
		
	}
	
	
	
	@Test
	public void saveUserTestCase() {
		user.setEmailId("@gmail.com");
		user.setFirstName("");
		user.setName("angel sharma");
		user.setPassword("angels@123");
boolean status=userDAO.saveOrUpdate(user);
assertEquals("save user test case",true,status);
	}
	
	@Ignore
	@Test
	public void getUserSuccessTestCase() {
		user=userDAO.get("seema@gmail.com");
assertNotNull("get user test case",user);
	}
	public void getUserFailureTestCase() {
		user=userDAO.get("angels@gmail.com");
assertNull("get user test case",user);
	}
	@Ignore
	@Test
	public void deleteUserSuccessTestCase()
	{
	boolean status=userDAO.delete("piyush@gmail.com");
	assertEquals("delete user success test case",true,status);
	}
	
	@Ignore
	@Test
	public void deleteUserFailureTestCase()
	{
	boolean status=userDAO.delete("angel@gmail.com");
	assertEquals("delete user failure test case",false,status);
	}
	
	@Ignore
	@Test
	public void getallUsersTestCase()
	{
		List<User> users=userDAO.list();
	
	assertEquals("get all users",3,users.size());
	}
	@Ignore
	@Test
	public void validateCredentialsTestCase()
	{
		user=userDAO.validate("angels@gmail.com", "angel@123");
		//assertNotNUll("validate test case",user);
	}
	
	


}*/