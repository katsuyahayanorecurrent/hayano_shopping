package com.example.demo.Classies;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.Entity.Order;

public class OrderHistory {
	
	private Order order;
	private List<OrderDetailHistory> orderDetails = new ArrayList<>();
	
	public OrderHistory() {
		super();
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public List<OrderDetailHistory> getOrderDetails() {
		return orderDetails;
	}
	public void setOrderDetails(List<OrderDetailHistory> orderDetails) {
		this.orderDetails = orderDetails;
	}
}
