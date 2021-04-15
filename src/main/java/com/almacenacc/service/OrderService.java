package com.almacenacc.service;
import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.almacenacc.DAO.OrderDAO;
import com.almacenacc.model.Order;

@RestController
public class OrderService {
	
	
	@Autowired
	private OrderDAO orderService;
	

	@GetMapping("/orders")
	public List<Order> getAllOrders(){
		return orderService.getAllOrders();
	}
	

	@GetMapping("/orders/{id}")
	public Order getOrderById(@PathVariable int id)
	{
		return orderService.getOrderById(id);
	}
	

	@GetMapping("clients/{idClient}/orders")
	public List<Order> getOrderByClient(@PathVariable int idClient){
		return orderService.getAllOrdersByClient(idClient);
	}
	

	@PostMapping("/clients/{idClient}/orders")
	public ResponseEntity<Object> createOrder(@PathVariable  int idClient,@RequestBody Order order)
	{
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{idClient}").buildAndExpand(idClient).toUri();
		orderService.addOrder(order);
		return ResponseEntity.created(location).build();
	}
	

	@PutMapping("/clients/{idClient}/orders/{idOrder}")
	public Order updateOrder(@PathVariable int idClient, @PathVariable int idOrder,@RequestBody Order order)
	{
		return orderService.updateOrder(idClient, order, idOrder);
	}
	

	@PostMapping("/clients/{idClient}/orders/{idOrder}/addProduct/{Productid}")
	public Order addProductToOrder(@PathVariable int idClient, @PathVariable int idOrder, @PathVariable int Productid){
		return orderService.addProductToOrder(idClient, idOrder, Productid);
	}
	

	@DeleteMapping("/clients/{idClient}/orders/{idOrder}")
	public void deleteOrderByClient(@PathVariable int idClient, @PathVariable int idOrder)
	{
		Order order = orderService.deleteOrderById(idClient, idOrder);
		if (order==null) {
			
		}
	}
	

}
