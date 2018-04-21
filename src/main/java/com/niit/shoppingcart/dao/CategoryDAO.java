package com.niit.shoppingcart.dao;

import java.util.List;
import com.niit.shoppingcart.domain.Category;

//Data Access object-DAO 
public interface CategoryDAO {


	public boolean saveOrUpdate(Category category);
	
	public Category getCategoryById(int categoryId);
	
	public boolean deleteCategoryById(int categoryId);
	
	public List<Category> getCategoriesByName(String name);
	
	public List<Category> getAllCategories();
}