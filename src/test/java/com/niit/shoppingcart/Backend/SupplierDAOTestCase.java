/*package com.niit.shoppingcart.Backend;

import static org.junit.Assert.*;


import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.shoppingcart.dao.SupplierDAO;
import com.niit.shoppingcart.domain.Supplier;



public class SupplierDAOTestCase {
	private static AnnotationConfigApplicationContext context;
	@Autowired
	private static SupplierDAO supplierDAO;
	@Autowired
	private static Supplier supplier;
	@BeforeClass
	public static void init() {
		context=new AnnotationConfigApplicationContext();
		context.scan("com.niit.shoppingcart"); 
		context.refresh();
		supplierDAO=(SupplierDAO)context.getBean("supplierDAO");
		supplier=(Supplier)context.getBean("supplier");
	}

	

	@Test
	public void saveSupplierDAOTestCase() {
		supplier.setId("Divyas9337");
		supplier.setName("Divya");
		supplier.setAddress("isnapur");
		boolean status=supplierDAO.saveOrUpdate(supplier);
		assertEquals("save your test case",true,status);
	}

}
*/