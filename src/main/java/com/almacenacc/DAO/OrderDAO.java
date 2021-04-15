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
	public final Integer TWELVEHOURS=43200000;
	public final Integer FIVEHOURS=18000000;
	
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

	
	public static void deleteDeliverycostFromOrdersgeneratedManually(){
		for (int i = 0; i < orders.size(); i++) {
			if (orders.get(i).getPrice()>100000) {
				Iterator<Product> iterator = orders.get(i).getProducts().iterator();
				orders.get(i).setPrice(orders.get(i).getPrice()-5000);
				while (iterator.hasNext()) {
					Product product2 = iterator.next();
					if (product2.getProductId()==0) {
						iterator.remove();
					}
				}
			}
		}
	}
	
	public List<Order> getAllOrders(){
		return orders;
	}
	
	public List<Order> getAllOrdersByClient (int idClient){
		List<Order> ClientsOrders = new ArrayList<>();
		for (Order order : orders) {
			if (order.getIdClient()==idClient) {
				ClientsOrders.add(order);
			}
		}
		return ClientsOrders;
	}
	
	public Order addOrderToAClient (int idClient, Order order){
		if (order.getIdOrder().equals(null)) {
			order.setIdOrder(++orderCounter);
		}
		order.setIdClient(idClient);
		deleteDelivery(order);
		orders.add(order);
		return order;
	}
	
	public Order getOrderById(int id)
	{
		for (Order order : orders) {
			if (order.getIdOrder()==id) {
				return order;
			}
		}
		return null;
	}
	
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
	
	public boolean isUpdatedTime(Date orderDate){
		Date actualDate = new Date();
		if (actualDate.getTime()- orderDate.getTime()<FIVEHOURS) {
			return true;
		}
		return false;
	}
	
	
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
	
	
	public boolean isCancelledTime(Date orderDate)
	{
		Date actualDate = new Date();
		if (actualDate.getTime()- orderDate.getTime()<TWELVEHOURS) {
			return true;
		}
		return false;
	}
	
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
	
	public Order addProductToOrder(int idClient, int idOrder, int productid)
	{
		ProductDAO products = new ProductDAO();
		Product product = products.findById(productid);
		Order order = deleteDelivery(getOrderById(idOrder));
		order.getProducts().add(product);
		return updateOrder(idClient, order, idOrder);
	}
	
	
	
	
	
	
	
	
	
}
