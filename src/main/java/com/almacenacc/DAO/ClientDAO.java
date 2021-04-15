package com.almacenacc.DAO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.almacenacc.model.Client;

@Component
public class ClientDAO {
	private static int userCounter=2;
	private static List<Client> clients = new ArrayList<>();
	static {
		clients.add(new Client(1, "ClienteDorian", "Carrera 11# 14-08", "12345"));
		clients.add(new Client(2, "ClienteTeresa", "Carrera 11# 14-09", "1234"));
		
	}
	
	public List<Client> findAll(){
		return clients;
	}
	
	public Client addClient(Client client) {
		if(client.getIdClient().equals(null)) {
			client.setIdClient(++userCounter);
		}
		clients.add(client);
		return client;
	}
	
	public Client findById(int id) {
		for (Client client : clients) {
			if (client.getIdClient()==id) {
				return client;
			}
		}
		return null;
	}
	
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
	

