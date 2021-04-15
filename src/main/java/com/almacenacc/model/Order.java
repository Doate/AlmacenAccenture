package com.almacenacc.model;
import java.util.List;
import java.util.Date;


public class Order {
	private Integer idOrder;
	private List<Product> products;
	private Integer idClient;
	private Double price;
	private Date date;
	private String state;

	public Order (Integer idOrder, List<Product> products, Integer idClient, Date date, String state) {
	this.idOrder=idOrder;
	this.products= products;
	this.date=date;
	this.idClient=idClient;
	this.state=state;
	this.price=calculatePrice(products);
	
	}
	
	@Override
	public String toString() {
		String totalorder = "Order [OrderId = " + idOrder +", Products=" + products +", idClient = " + idClient + 
				", Totalprice = "+ price + ", PurchaseDate = " + date + ", OrderState"+ state +"]";
		return totalorder;
	}

	public Integer getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(Integer idOrder) {
		this.idOrder = idOrder;
	}


	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
	public Integer getIdClient() {
		return idClient;
	}

	public void setIdClient(Integer idClient) {
		this.idClient = idClient;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getstate() {
		return state;
	}

	public void setstate(String state) {
		this.state = state;
	}
	
	public Double calculatePrice(List<Product> products)
	{
		double precioProductos=0;
		for (Product product : products) {
			precioProductos+=product.getPrice();
		}
		price=precioProductos;
		return price;
	}

}
