package com.almacenacc.DAO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.almacenacc.model.Client;

@Component
public class ClientDAO {
	
	/**
	 * Here we define a user counter to maintain the 
	 * id on the list 
	 */
	private static int userCounter=2;
	private static List<Client> clients = new ArrayList<>();
	
	/**
	 * Here we initialize a static block that can allow us
	 *  to load a certain amount of bills to interact with the 
	 *  methods created in the class
	 */
	
	static {
		clients.add(new Client(1, "ClienteDorian", "Carrera 11# 14-08", "12345"));
		clients.add(new Client(2, "ClienteTeresa", "Carrera 11# 14-09", "1234"));
		
	}
	
	
	/**
	 * Return the list of the clients 
	 * @return Client list
	 */
	public List<Client> findAll(){
		return clients;
	}
	
	/**
	 * Adds a client to the list of registered clients
	 * 
	 * @param client
	 * @return the new client that was registered
	 */
	public Client addClient(Client client) {
		if(client.getIdClient().equals(null)) {
			client.setIdClient(++userCounter);
		}
		clients.add(client);
		return client;
	}
	
	/**
	 * Return the client that is registered in the list with a 
	 * given id
	 * 
	 * @param id
	 * @return the client registered by a given id and if there is no client
	 * returns null
	 */
	public Client findById(int id) {
		for (Client client : clients) {
			if (client.getIdClient()==id) {
				return client;
			}
		}
		return null;
	}
	
	/**
	 * Deletes a client given a certain id
	 * @param id
	 * Since it deletes the client given the id provided 
	 * this method doesn't return any value
	 */
	public Client deleteClient (int id) {
		Iterator<Client> iterator = clients.iterator();
		while (iterator.hasNext()) {
			Client client = iterator.next();
			if(client.getIdClient()==id) {
				iterator.remove();
				return client;
			}
		}
		return null;
	}
	
}
	

