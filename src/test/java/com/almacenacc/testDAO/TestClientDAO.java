package com.almacenacc.testDAO;

import org.springframework.stereotype.Component;

import com.almacenacc.DAO.ClientDAO;
import com.almacenacc.model.Client;

import junit.framework.TestCase;


@Component
public class TestClientDAO extends TestCase {
	
	private ClientDAO clientDAOTest;
	private final static Client telamet= new Client(3, "Yolojose", "calle12+1","1234");

	
	private void root() {
		clientDAOTest = new ClientDAO();
	}
	
	public void testaddClient() {
		root();
		assertNotNull("No se puede agregar se necesita un objeto", clientDAOTest.addClient(telamet));
	}
	
	
	public void testfindByIdclient () {
		root();
		
		assertEquals(null, 	clientDAOTest.findById(0));
	}
	
	public void testfindByIdclient1 () {
		root();
		assertNotNull("El usuario no existe", 	clientDAOTest.findById(2));
	}
	
	
	
	public void testfindByIdclient2 () {
		root();
		assertEquals(telamet, 	clientDAOTest.findById(3));
	}
	
	
	
	public void testdeleteClient (){
		root();
		assertNotNull("Cliente no existe", clientDAOTest.deleteClient(4));
	}
	
	public void testdeleteClient2 () {
		root();
		assertNotNull("Cliente no existe", clientDAOTest.deleteClient(1));
	}
	
	
	

}
