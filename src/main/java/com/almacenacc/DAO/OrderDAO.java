package com.almacenacc.DAO;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.almacenacc.model.Bill;
import com.almacenacc.model.Order;
import com.almacenacc.model.Product;

@Component
public class OrderDAO {
	
	/**
	 * Here we define the conditions regarding the time of the order 
	 * that can change the state of the order 
	 */
	public final Integer TWELVEHOURS=43200000;
	public final Integer FIVEHOURS=18000000;
	
	/**
	 *  Here we define a user counter to maintain the 
	 *  id's of the array of orders
	 */
	private static Integer orderCounter=3;
	
	@Autowired
	private BillDAO billService;
	private static List<Order> orders= new ArrayList<>();
	
	static {
		orders.add(new Order(1, new ProductDAO().findAll(), 1, new Date(), "ACCEPTED"));
		orders.add(new Order(2, new ProductDAO().findAll(), 2, new Date(), "ACCEPTED"));
		orders.add(new Order(3, new ProductDAO().findAll(), 2, new Date(), "ACCEPTED"));
		
	
		deleteDeliverycostFromOrdersgeneratedManually();
	}

	/**
	 * Method that checks the total price of the order
	 * and removes the delivery cost
	 * 
	 */
	public static void deleteDeliverycostFromOrdersgeneratedManually(){
		for (int i = 0; i < orders.size(); i++) {
			if (orders.get(i).getPrice()>100000) {
				Iterator<Product> iterator = orders.get(i).getProducts().iterator();
				orders.get(i).setPrice(orders.get(i).getPrice()-3000);
				while (iterator.hasNext()) {
					Product product2 = iterator.next();
					if (product2.getProductId()==0) {
						iterator.remove();
					}
				}
			}
		}
	}
	
	
	/**
	 * Return the orders that were registered on
	 * the array
	 * @return all the orders
	 */
	public List<Order> getAllOrders(){
		return orders;
	}
	
	/**
	 * Returns all the orders made by a certain client
	 * @param idClient
	 * @return list of orders made by the client
	 */
	public List<Order> getAllOrdersByClient (int idClient){
		List<Order> ClientsOrders = new ArrayList<>();
		for (Order order : orders) {
			if (order.getIdClient()==idClient) {
				ClientsOrders.add(order);
			}
		}
		return ClientsOrders;
	}
	/**
	 * adds an order to a client given a certain id
	 * @param idClient
	 * @param order
	 * @return the created order
	 */
	
	public Order addOrderToAClient (int idClient, Order order){
		if (order.getIdOrder().equals(null)) {
			order.setIdOrder(++orderCounter);
		}
		order.setIdClient(idClient);
		deleteDelivery(order);
		orders.add(order);
		return order;
	}
	
	
	/**
	 * Return an order given a certain id
	 * @param id
	 * @return
	 */
	public Order getOrderById(int id)
	{
		for (Order order : orders) {
			if (order.getIdOrder()==id) {
				return order;
			}
		}
		return null;
	}
	
	
	/**
	 * Adds a new order to the array and verifies the price
	 * to determine if the purchase applies for a delivery cost
	 * @param order
	 * @return
	 */
	public Order addOrder(Order order)
	{
		if (order.getIdOrder()==null) {
			order.setIdOrder(++orderCounter);
		}
		if (order.getDate()==null) {
			order.setDate(new Date());
		}
		double verifyPrice = order.calculatePrice(order.getProducts());
		if (verifyPrice<100000) {
			order.getProducts().add(new Product(0, "Delivery", 3000.00));
			order.setPrice(verifyPrice+5000);
		}else {
			order.getProducts().add(new Product(0, "Delivery", 0.00));
			order.setPrice(verifyPrice);
		}
		orders.add(order);
		billService.addBill(new Bill(null, order));
		return order;
	}
	
	/**
	 * updates an order of a certain client verifies if it applies
	 * for a delivery service and verifies if it meets the condition
	 * "if five hours have not passed" to update the order
	 * 
	 * @param idClient
	 * @param order
	 * @param idOrder
	 * @return order with updated data
	 */
	
	public Order updateOrder (int idClient, Order order , int idOrder){
		
		Order newOrder = new Order(idOrder, order.getProducts(), idClient, new Date(), order.getstate());
		
		for (int i = 0; i < orders.size(); i++) {
			if (orders.get(i).getIdOrder()==idOrder&&orders.get(i).getIdClient()==idClient) {
				if (isUpdatedTime(order.getDate())==true) {
					if (newOrder.getPrice()>=orders.get(i).getPrice()) {
						deleteDelivery(newOrder);
						orders.set(i, newOrder);
						billService.updateBill(idOrder, newOrder);
						return orders.get(i);
					}
				}

			}
		}
		return null;
	}
	
	/**
	 * Auxiliary method that verifies if less than five hours 
	 * had elapsed to determine if the method above updateOrder
	 * can go on
	 * @param orderDate
	 * @return
	 */
	public boolean isUpdatedTime(Date orderDate){
		Date actualDate = new Date();
		if (actualDate.getTime()- orderDate.getTime()<FIVEHOURS) {
			return true;
		}
		return false;
	}
	
	
	/**
	 * Deletes an order given a client id and
	 * an order id and checks the condition of twelve hours
	 * to generate a bill
	 * 
	 * @param idClient
	 * @param idOrder
	 * @return
	 */
	
	public Order deleteOrderById(int idClient, int idOrder){
		Iterator<Order> iterator = orders.iterator();
		while (iterator.hasNext()) {
			Order order = iterator.next();
			if (order.getIdOrder()==idOrder && order.getIdClient()==idClient) {
				if (isCancelledTime(order.getDate())==true) {
					iterator.remove();
					billService.deleteBillbyIdOrder(idOrder);
					return order;
				}
				else {
					order.setstate("CANCELLED");
					double newPrice= order.getPrice();
					newPrice = (newPrice / 100)*10;
					order.setPrice(newPrice);
					billService.updateBill(idOrder, order);
				}
			}
		}
		return null;
	}
	
	/**
	 * Auxiliary method of the implemented method above to verify
	 * if twelve hours had elapsed
	 * @param orderDate
	 * @return
	 */
	public boolean isCancelledTime(Date orderDate)
	{
		Date actualDate = new Date();
		if (actualDate.getTime()- orderDate.getTime()<TWELVEHOURS) {
			return true;
		}
		return false;
	}
	
	/**
	 * Auxiliary method that verifies if an order 
	 * incurs on delivery fees
	 * @param order
	 * @return
	 */
	
	public Order deleteDelivery (Order order)
	{
		if (order.getPrice()>100000) {
			Iterator<Product> iterator = order.getProducts().iterator();
			while (iterator.hasNext()) {
				Product product2 = iterator.next();
				if (product2.getProductId()==0) {
					iterator.remove();
					return order;
				}
			}
		}
		return order;
	}
	
	/**
	 * Adds a product to a given order to a certain client
	 * @param idClient
	 * @param idOrder
	 * @param productid
	 * @return updated order
	 */
	
	public Order addProductToOrder(int idClient, int idOrder, int productid)
	{
		ProductDAO products = new ProductDAO();
		Product product = products.findById(productid);
		Order order = deleteDelivery(getOrderById(idOrder));
		order.getProducts().add(product);
		return updateOrder(idClient, order, idOrder);
	}
	
	
	
	
	
	
	
	
	
}
