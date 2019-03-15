package com.cherry.netty.demo.domain;

import com.cherry.netty.utils.JsonUtil;

public class Order {
	private long orderNumber;
	private Customer customer;
	private Address billto;
	private Shipping shipping;
	private Address shipto;
	private float total;
	public long getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(long orderNumber) {
		this.orderNumber = orderNumber;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Address getBillto() {
		return billto;
	}
	public void setBillto(Address billto) {
		this.billto = billto;
	}
	public Shipping getShipping() {
		return shipping;
	}
	public void setShipping(Shipping shipping) {
		this.shipping = shipping;
	}
	public Address getShipto() {
		return shipto;
	}
	public void setShipto(Address shipto) {
		this.shipto = shipto;
	}
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
	@Override
	public String toString() {
		return JsonUtil.toJson(this);
	}
	
	

}
