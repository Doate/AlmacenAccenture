package com.almacenacc.testDAO;

import java.util.Date;

import org.springframework.stereotype.Component;
import junit.framework.TestCase;

import com.almacenacc.DAO.BillDAO;
import com.almacenacc.DAO.OrderDAO;
import com.almacenacc.DAO.ProductDAO;
import com.almacenacc.model.Bill;
import com.almacenacc.model.Order;

@Component
public class TestBillDAO extends TestCase {
	private BillDAO billDAOTest;
	private final static Order order = new Order(4, new ProductDAO().findAll(), 2, new Date(), "ACCEPTED");
	private final static Bill bill = new Bill(4, order);
	private void root(){
		billDAOTest= new BillDAO();
		
		
	}
	
	public void testgetAllBills(){
		root();
		assertSame("Facutras no encontradas ",3, billDAOTest.getAllBills().size());
	}
	
	public void testgetBillbyId() {
		root();
		assertNotNull("Facutra no existe", billDAOTest.getBillbyId(1));
	}
	
	public void testgetBillbyIderror() {
		root();
		assertNotNull("Facutra no existe", billDAOTest.getBillbyId(4));
	}
	
	public void testaddBill() {
		root();
		assertSame(bill, billDAOTest.addBill(bill));
	}
	

}