package com.example.demo.Classies;

import com.example.demo.Entity.Items;
import com.example.demo.Entity.OrderDetail;

public class OrderDetailHistory {
	private OrderDetail orderDetail;
	private Items item = new Items();
	
	public OrderDetailHistory() {
		super();
	}

	public OrderDetail getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(OrderDetail orderDetail) {
		this.orderDetail = orderDetail;
	}

	public Items getItem() {
		return item;
	}

	public void setItem(Items item) {
		this.item = item;
	}
	
	
}
