package com.almacenacc.DAO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.almacenacc.model.Product;

@Component
public class ProductDAO {
	private static Integer productCounter = 3;
	private static List<Product> products = new ArrayList<>();
	
	static {
		products.add(new Product(0, "Delivery", 3000.00));
		products.add(new Product(1, "Product1", 75000.00));
		products.add(new Product(2, "Product2", 30000.00));
		products.add(new Product(3, "Product3", 60000.00));
		
	}
	public List<Product> findAll(){
		return products;
	}
	public Product findById(int id) {
		for (Product product : products) {
			if (product.getProductId()==id) {
				return product;
			}
		}
		return null;
	}
	public Product addProduct(Product product) {
		if (product.getProductId().equals(null)) {
			product.setProductId(++productCounter);
		}
		products.add(product);
		return product;
	}
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
