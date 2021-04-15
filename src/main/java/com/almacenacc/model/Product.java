package com.almacenacc.model;

public class Product {
	private Integer productid;
	private String productname;
	private Double price;
	
	public Product(Integer productid, String productname, Double price) {
		this.productid=productid;
		this.productname=productname;
		this.price=price;
	}
	
	public Integer getProductId() {
		return productid;
	}
	public void setProductId(Integer productid) {
		this.productid = productid;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		String ProductData ="Product [ProductId=" + productid + ", ProductName=" + productname +
				", price=" + price + "]";
		return ProductData;
	}

}
