/*package com.niit.shoppingcart.Backend;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.shoppingcart.dao.CategoryDAO;
import com.niit.shoppingcart.domain.Category;

public class CategoryDAOTestCase {

	
		private static AnnotationConfigApplicationContext context;
		@Autowired
		private static CategoryDAO categoryDAO;
		@Autowired
		private static Category category;
		@BeforeClass
		public static void init() {
			context=new AnnotationConfigApplicationContext();
			context.scan("com.niit.shoppingcart"); 
			context.refresh();
			categoryDAO=(CategoryDAO)context.getBean("categoryDAO");
			category=(Category)context.getBean("category");
		}
		@Test
		public void saveCategoryTestCase() {
			category.setId("Womens001");
			category.setName("Women");
			category.setDescription("this is Women category");
			
			boolean status=categoryDAO.saveOrUpdate(category);
			assertEquals("save your test case",true,status);
		}
			
	}*/