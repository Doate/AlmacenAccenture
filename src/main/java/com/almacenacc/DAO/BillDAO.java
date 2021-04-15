package com.almacenacc.DAO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.almacenacc.model.Bill;
import com.almacenacc.model.Order;

@Component
public class BillDAO {
	private static int billCounter=3;
	private static List<Bill> bills = new ArrayList<>();
	/**
	 * Here we initialize a static block that can allow us
	 *  to load a certain amount of bills to interact with the 
	 *  methods created in the class
	 */
	static {
		bills.add(new Bill (1, new OrderDAO().getOrderById(1)));
		bills.add(new Bill (2, new OrderDAO().getOrderById(2)));
		bills.add(new Bill (3, new OrderDAO().getOrderById(3)));
		
	}
	
	/**
	 * Return all the registered bills that are loaded in the 
	 * memory
	 * @return
	 */
	public List<Bill> getAllBills()
	{
		return bills;
	}
	
	/**
	 * Return the registered bill given a certain id
	 * @param id
	 * @return the bill given a certain id
	 */
	
	public Bill getBillbyId(int id) {
		for (Bill bill : bills) {
			if (bill.getBillid()==id) {
				return bill;
			}
		}
		return null;
	}
	
	/**
	 * Adds a new bill to the list defined before
	 * @param bill
	 * @return the new bill added
	 */
	
	
	public Bill addBill(Bill bill) {
		if (bill.getBillid()==null) {
			bill.setBillid(++billCounter);
		}
		bills.add(bill);
		return bill;
	}
	
	/**
	 * Updates a bill given; generates a new one with the 
	 * updated data and replaces the previous bill
	 * @param idOrder
	 * @param order
	 */
	public void updateBill (int idOrder, Order order ){
		for (int i=0; i<bills.size(); i++) {
			if (bills.get(i).getOrder().getIdOrder()==idOrder) {
				Bill neworder = addBill(new Bill(null, order));
				bills.remove(i);
				bills.set(i, neworder);			
				}
		}
		
	}
	
	/**
	 * Deletes a bill given a certain id
	 * @param idOrder
	 * @return
	 */
	
	
	public Bill deleteBillbyIdOrder(int idOrder) {
		Iterator<Bill> iterator = bills.iterator();
		while (iterator.hasNext()) {
			Bill bill = (Bill) iterator.next();
			if (bill.getOrder().getIdOrder()==idOrder) {
				iterator.remove();
				return bill;
			}
		}
		return null;
	}
	
}
