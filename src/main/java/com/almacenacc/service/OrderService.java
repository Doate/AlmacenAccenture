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
	

	@GetMapping("/pedidos")
	public List<Order> getAllOrders(){
		return orderService.getAllOrders();
	}
	

	@GetMapping("/pedidos/{id}")
	public Order getOrderById(@PathVariable int id)
	{
		return orderService.getOrderById(id);
	}
	

	@GetMapping("clientes/{idCliente}/pedidos")
	public List<Order> getPedidoByCliente(@PathVariable int idClient){
		return orderService.getAllOrdersByClient(idClient);
	}
	

	@PostMapping("/clientes/{idCliente}/pedidos")
	public ResponseEntity<Object> createOrder(@PathVariable  int idClient,@RequestBody Order order)
	{
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{idClient}").buildAndExpand(idClient).toUri();
		orderService.addOrder(order);
		return ResponseEntity.created(location).build();
	}
	

	@PutMapping("/clientes/{idCliente}/pedidos/{idPedido}")
	public Order updateOrder(@PathVariable int idClient, @PathVariable int idOrder,@RequestBody Order order)
	{
		return orderService.updateOrder(idClient, order, idOrder);
	}
	

	@PostMapping("/clientes/{idCliente}/pedidos/{idPedido}/addProduct/{idProducto}")
	public Order addProductToOrder(@PathVariable int idClient, @PathVariable int idOrder, @PathVariable int Productid){
		return orderService.addProductToOrder(idClient, idOrder, Productid);
	}
	

	@DeleteMapping("/clientes/{idCliente}/pedidos/{idPedido}")
	public void deletePedidoByCliente(@PathVariable int idCliente, @PathVariable int idPedido)
	{
		Order order = orderService.deleteOrderById(idCliente, idPedido);
		if (order==null) {
			
		}
	}
	

}
