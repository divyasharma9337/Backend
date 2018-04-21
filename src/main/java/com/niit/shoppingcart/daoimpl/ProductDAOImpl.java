package com.niit.shoppingcart.daoimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.shoppingcart.dao.ProductDAO;
import com.niit.shoppingcart.domain.Product;


@Transactional
@Repository("productDAO") //will create instance of UserDAOImpl and the name will userDAOImpl
public class ProductDAOImpl implements ProductDAO {
	private static Logger log = LoggerFactory.getLogger(ProductDAOImpl.class);
	@Autowired
	SessionFactory sessionFactory;
	@SuppressWarnings("unused")
	@Autowired
	private Product product;
	@SuppressWarnings("unused")
	private Restrictions Restrictions;
	public ProductDAOImpl(SessionFactory sessionFactory) {
		log.info("ProductDAOImpl : getSessionFactory");
		this.sessionFactory=sessionFactory;
	}

	public boolean saveOrUpdate(Product product) {
		try {
			Session session=sessionFactory.openSession();
			Transaction tx=session.beginTransaction();
			session.saveOrUpdate(product);
			tx.commit();
			session.clear();
		} catch (HibernateException e) {
			
			e.printStackTrace();
		}
		
		return true;
	}

	public Product getProductById(int productId) {
		log.info("ProductDAOImpl : get Products details by Id -- "+productId);
		Product product = (Product) sessionFactory.getCurrentSession().createQuery("select p from Product p where id = :productId")
				.setParameter("productId", productId).uniqueResult();
		System.out.println(product.toString());
		log.info("ProductDAOImpl : Successful retrieval of Products details  -- "+product.toString());
		
		return product;	
	}

	public boolean deleteProductById(int productId) {
		log.info("ProductDAOImpl : get Sessionfactory");
		Session session = sessionFactory.getCurrentSession();
		log.info("ProductDAOImpl : load Product by Id --"+productId);
		Object peristanceInstance = session.load(Product.class, productId);
		log.info("ProductDAOImpl :  delete Product by Id --"+productId);
		if(peristanceInstance != null){
			session.delete(peristanceInstance);
			System.out.println("Product deleted successfully");
			log.info("ProductDAOImpl : Product deleted Successfully with product id --"+productId);
		}
		
		return true;
	}

	public List<Product> getAllProducts() {
		log.info("ProductDAOImpl : get All Products details");
		List<Product> products = sessionFactory.getCurrentSession().createQuery("from Product",Product.class).list();
		return products;
	}

	public List<Product> getProductBySearchText(String[] searchString) {
		String hql= "from Product where (lower(name) like '%"+searchString[0].toLowerCase()+"%' or lower(descritpion) like '%"+searchString[0].toLowerCase()+"%')";
for(int i=1;i<searchString.length;i++){
			
			hql += " or (lower(productName) like '%"+searchString[i].toLowerCase()+"%' or lower(description) like '%"+searchString[i].toLowerCase()+"%')";
		}
Session session = sessionFactory.openSession();
session.beginTransaction();
List<Product> products = session.createQuery(hql,Product.class).list();
session.getTransaction().commit();
session.close();
		return products;
	}

	public List<Product> getAllProductsByName(String searchString) {
		String searchArray[] = searchString.split(" ");
		String hql = "from Product";
		if(searchArray.length == 0){
			
			hql += " where (lower(name) like '%"+searchString.toLowerCase()+"%' or lower(description) like '%"+searchString.toLowerCase()+"%')";
		}else{
			
			hql += " where (lower(name) like '%"+searchArray[0].toLowerCase()+"%' or lower(description) like '%"+searchArray[0].toLowerCase()+"%')";
			for(int i=1;i<searchArray.length;i++){
				
				hql += " or (lower(name) like '%"+searchArray[i].toLowerCase()+"%' or lower(description) like '%"+searchArray[i].toLowerCase()+"%')";
			}
		}
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		List<Product> products = session.createQuery(hql,Product.class).list();
		session.getTransaction().commit();
		session.close();
		return products;
	}

	public List<Product> getAllProductForCategory(int catId) {
		log.info("ProductDAOImpl : get all Products under given Category Id -- "+catId);
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		List<Product> products = session.createQuery("from Product where categoryId = :catId",Product.class).setParameter("catId", catId).list();
		session.getTransaction().commit();
		session.close();
		return products;
	}

	public List<Product> getProductByBrand(String brandName) {
		String queryString = "from Product where lower(brandName) = :brandName";
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		List<Product> products = session.createQuery(queryString,Product.class).setParameter("brandName", brandName.toLowerCase()).list();
		session.getTransaction().commit();
		session.close();
		
		return products;
	}	
}