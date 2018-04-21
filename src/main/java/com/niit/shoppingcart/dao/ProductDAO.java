package com.niit.shoppingcart.dao;


import java.util.List;
import com.niit.shoppingcart.domain.Product;


public interface ProductDAO {
	
	public boolean saveOrUpdate(Product product);
	
	public Product getProductById(int productId);
	
	public boolean deleteProductById(int productId);
		
	public List<Product> getAllProducts();
	
	public List<Product> getAllProductForCategory(int catId);
	
	public List<Product> getProductByBrand(String brandName);
	
	public List<Product> getProductBySearchText(String[] searchString);	
	
	public List<Product> getAllProductsByName(String searchString);	
	
}