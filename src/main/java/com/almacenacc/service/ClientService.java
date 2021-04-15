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

import com.almacenacc.DAO.ClientDAO;
import com.almacenacc.model.Client;

@RestController
public class ClientService {
	@Autowired
	private ClientDAO clientService;
	

	@GetMapping("/clients")
	public List<Client> getAllClients()
	{
		return clientService.findAll();
	}
	

	@GetMapping("clients/{id}")
	public Client getClientById(@PathVariable int id)
	{
		if (clientService.findById(id)==null) {
			
		}
		return clientService.findById(id);
		
	}
	

	@PostMapping("/clients")
	public ResponseEntity<Object> addClient(@RequestBody Client client)
	{
		clientService.addClient(client);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(client.getIdClient()).toUri();
		return ResponseEntity.created(location).build();
	}
	

	@DeleteMapping("/clients/{id}")
	public void deleteByClientId(@PathVariable int id)
	{
		Client client= clientService.deleteClient(id);
		if (client.equals(null)) {
			//TODO
		}
	}

}
