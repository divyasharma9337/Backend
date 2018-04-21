package com.niit.shoppingcart.dao;


import java.util.List;
import com.niit.shoppingcart.domain.Supplier;

//Data Access object-DAO 
public interface SupplierDAO {
public boolean saveOrUpdate(Supplier supplier);
	
	public Supplier getSupplierById(int supplierId);
	
	public boolean deleteSupplierById(int supplierId);
		
	public List<Supplier> getAllSuppliers();
	

}