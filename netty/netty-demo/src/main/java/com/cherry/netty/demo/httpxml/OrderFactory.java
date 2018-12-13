package com.cherry.netty.demo.httpxml;

import com.cherry.netty.demo.domain.Address;
import com.cherry.netty.demo.domain.Customer;
import com.cherry.netty.demo.domain.Order;
import com.cherry.netty.demo.domain.Shipping;
import com.cherry.netty.utils.JsonUtil;

public class OrderFactory {

	public static Object create(long  orderID) {
		Address address = new Address();
		address.setCity("南京市");
		address.setCountry("中国");
		address.setPostCode("123321");
		address.setState("江苏省");
		address.setStreet1("龙眠大道");
		
		Customer customer = new Customer();
		customer.setCustomerNumber(orderID);
		customer.setFirstName("李");
		customer.setLastName("寻欢");
		
		Order order = new Order();
		order.setBillto(address);
		order.setCustomer(customer);
		order.setOrderNumber(orderID);
		order.setShipping(Shipping.INTERNATIONAL_MAIL);
		order.setShipto(address);
		order.setTotal(9999.99f);
		
		System.out.println("OrderFactory.create(): "+JsonUtil.toJson(order));
		return order;
	}

}
