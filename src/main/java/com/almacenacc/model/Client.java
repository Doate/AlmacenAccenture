package com.almacenacc.model;

public class Client {

	private Integer idClient;
	private String Name;
	private String Address;
	private String idDocument;
	
	public Client (Integer idClient, String Name, String Address, String idDocument) {
		this.idClient = idClient;
		this.Name = Name;
		this.Address = Address;
		this.idDocument = idDocument;
	}

	public Integer getIdClient() {
		return idClient;
	}

	public void setIdClient(Integer idClient) {
		this.idClient = idClient;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getIdDocument() {
		return idDocument;
	}

	public void setIdDocument(String idDocument) {
		this.idDocument = idDocument;
	}
	
	@Override
	public String toString() {
		String clientdata = "Client [idClient = " + idClient +", Name = " + Name + 
				", Address = "+ Address + ", idDocument = " + idDocument + "]";
		return clientdata;
	}
	
}
