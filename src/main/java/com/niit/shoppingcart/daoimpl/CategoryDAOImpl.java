package com.niit.shoppingcart.daoimpl;


import java.util.List;


import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.shoppingcart.dao.CategoryDAO;
import com.niit.shoppingcart.domain.Category;

@Transactional
@Repository("categoryDAO") 
public class CategoryDAOImpl implements CategoryDAO {
	private static Logger log = LoggerFactory.getLogger(CategoryDAOImpl.class);
	//first -inject hibernate session 
	//@Autowire
	@Autowired //session factory will automatically inject in this class
	 SessionFactory sessionFactory;
	@SuppressWarnings("unused")
	@Autowired
	private Category category;
	@SuppressWarnings("unused")
	private Restrictions Restrictions;
	public CategoryDAOImpl(SessionFactory sessionFactory) {
		log.info("CategoryDAOImpl : getSessionFactory");
		this.sessionFactory=sessionFactory;
		
	}
	public boolean saveOrUpdate(Category category) {
		try {
			Session session=sessionFactory.openSession();
			Transaction tx=session.beginTransaction();
			
			session.saveOrUpdate(category);
			tx.commit();
			session.clear();
			
		} catch (Exception e) {
			
		}
		return true;
	}
	
	public Category getCategoryById(int categoryId) {
		log.info("CategoryDAOImpl : get Category detail by Id -- "+categoryId);		
		Category category = (Category) sessionFactory.getCurrentSession().createQuery("select c from Category c where id = :categoryId")
																.setParameter("categoryId", categoryId).uniqueResult();
		System.out.println(category.toString());
		log.info("CategoryDAOImpl : get Category detail by Id --category detail "+category.toString());
		return category;
	}
	public boolean deleteCategoryById(int categoryId) {
		
		log.info("CategoryDAOImpl : delete Category detail by Id --getSession ");
		Session session = sessionFactory.getCurrentSession();
		log.info("CategoryDAOImpl : delete Category detail by Id --load Category record --"+categoryId);
		Object peristanceInstance = session.load(Category.class, categoryId);
		
		log.info("CategoryDAOImpl : delete Category detail by Id --delete selected category --"+categoryId);
		if(peristanceInstance != null){
			session.delete(peristanceInstance);
			System.out.println("Category deleted successfully");
			log.info("CategoryDAOImpl : Category deleted successfully --"+categoryId);
		}
		
		return true;
	}
	
	
	public List<Category> getCategoriesByName(String name) {
		log.info("CategoryDAOImpl : getCategoriesByName-- getCurrentSession");
		 Session session = sessionFactory.getCurrentSession();
		
		 log.info("CategoryDAOImpl : getCategoriesByName-- select Category where categoryName contains "+name);
		 List<Category> categories = session.createQuery("from Category where categoryName like :name",Category.class)
				 					.setParameter("name", "'%"+name+"%'").list();
		 return (List<Category>)categories;
	}

	
	public List<Category> getAllCategories() {
		log.info("CategoryDAOImpl : getAll the categories");
		List<Category> categories = sessionFactory.getCurrentSession().createQuery("from Category",Category.class).list();
		return categories;
	}
	}