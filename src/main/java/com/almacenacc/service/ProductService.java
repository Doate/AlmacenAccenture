package com.almacenacc.service;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.almacenacc.DAO.ProductDAO;
import com.almacenacc.model.Product;

@RestController
public class ProductService {
	@Autowired
	private ProductDAO productService;


	@GetMapping("/products")
	public List<Product> getAllProducts()
	{
		return productService.findAll();
	}


	@GetMapping("products/{id}")
	public Product getProductById(@PathVariable int id)
	{
		return productService.findById(id);
	}


	@PostMapping("/products")
	public ResponseEntity<Object> addProduct(@RequestBody Product product)
	{
		productService.addProduct(product);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(product.getProductId()).toUri();
		return ResponseEntity.created(location).build();
	}


	@DeleteMapping("/products/{id}")
	public void deleteByProductId(@PathVariable int id)
	{
		Product product = productService.deleteProductByid(id);
		if (product.equals(null)) {
		}
	}
	

}
