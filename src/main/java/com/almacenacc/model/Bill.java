package com.almacenacc.model;


public class Bill {
	private Integer Billid;
	private Order order;
	private double iva;
	
	public Bill(Integer Billid, Order order) {
		this.Billid=Billid;
		this.order = order;
		this.iva=setIVA();
	}

	public Integer getBillid() {
		return Billid;
	}

	public void setBillid(Integer Billid) {
		this.Billid = Billid;
	}

	public Order getOrder() {	
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public double getIVA() {
		return iva;
	}

	public double setIVA() {
		return this.iva = (order.getPrice()*(0.19));
	}
	@Override
	public String toString() {
		String Billdata = "Bill [BillId = " + Billid +", Order = " + order + 
				", IVA = "+ iva  + "]";
		return Billdata;
	}
}
