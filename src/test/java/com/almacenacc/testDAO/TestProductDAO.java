package com.almacenacc.testDAO;

import org.springframework.stereotype.Component;


import com.almacenacc.DAO.ProductDAO;
import com.almacenacc.model.Product;

import junit.framework.TestCase;
@Component


public class TestProductDAO  extends TestCase {
		private ProductDAO productDAOTest;
		private static final Product product4 = new Product(4, "Product4", 60000.00);
		
		
		private void root() {
			productDAOTest = new ProductDAO();
		}
		
	public 	void testaddProduct() {
		root();
		assertNotNull("No se puede agregar producto se necesita un objeto producto", productDAOTest.addProduct(product4));
		
	}

	public void testfindProductById() {
		root();
		assertEquals(null, productDAOTest.findById(0));
	}
	
	public void testfindProductByIderror() {
		root();
		assertEquals(null, productDAOTest.findById(5));
	}
	
	public void testfindProductById1() {
		root();
		assertNotNull("El producto no existe", productDAOTest.findById(4));
	}
	
	public void testdeleteProductByid() {
		root();
		assertNotNull("Producto no existe no puedes eliminar", productDAOTest.deleteProductByid(1));
	}
	
	public void testdeleteProductByiderror() {
		root();
		assertNotNull("Producto no existe no puedes eliminar", productDAOTest.deleteProductByid(5));
	}
	
	public void testFindAllproducts() {
		root();
		assertSame("Productos no encontrados", 4, productDAOTest.findAll().size());
	}
	
	public void testFindAllproductsError1() {
		root();
		assertSame("Lo ingresado no es lo esperado", "4", productDAOTest.findAll().size());
		
	}
	
	public void testFindAllProductsError2(){
        root();
        assertSame("Los objetos no son iguales", "1", productDAOTest.findAll().get(1));
    }
	
	

}
