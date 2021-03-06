package com.almacenacc.DAO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.almacenacc.model.Product;

@Component
public class ProductDAO {
	
	/**
	 *  Here we define a user counter to maintain the 
	 *  id's of the array of products
	 */
	private static Integer productCounter = 3;
	private static List<Product> products = new ArrayList<>();
	
	/**
	 * Here we initialize a static block that can allow us
	 *  to load a certain amount of bills to interact with the 
	 *  methods created in the class
	 */
	static {
		products.add(new Product(0, "Delivery", 3000.00));
		products.add(new Product(1, "Product1", 75000.00));
		products.add(new Product(2, "Product2", 30000.00));
		products.add(new Product(3, "Product3", 60000.00));
		
	}
	/**
	 * Method that shows the array of all of the products
	 * @return array of products
	 */
	
	public List<Product> findAll(){
		return products;
	}
	
	/**
	 * Method that finds a product given a id
	 * @param id
	 * @return the product with the 
	 * corresponding id
	 */
	public Product findById(int id) {
		for (Product product : products) {
			if (product.getProductId()==id) {
				return product;
			}
		}
		return null;
	}
	
	
	/**
	 * Adds a product to the array of products
	 * @param product
	 */
	
	public Product addProduct(Product product) {
		if (product.getProductId().equals(null)) {
			product.setProductId(++productCounter);
		}
		products.add(product);
		return product;
	}
	
	/**
	 * Deletes a product on the array given an id
	 * 
	 * @param id
	 * 
	 */
	public Product deleteProductByid(int id) {
		Iterator<Product>iterator=products.iterator();
		while (iterator.hasNext()) {
			Product product = iterator.next();
			if (product.getProductId()==id) {
				iterator.remove();
				return product;
			}
		}
		return null;
	}
	
}
