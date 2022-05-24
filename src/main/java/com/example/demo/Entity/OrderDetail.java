package com.example.demo.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="order_detail")
public class OrderDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="ordered_id")
	private Integer orderedId;
	
	@Column(name="item_id")
	private Integer itemId;
	
	@Column(name="quantity")
	private Integer quantity;
	
	

	public OrderDetail() {
		super();
	}

	public OrderDetail(Integer ordered_id, Integer item_id, Integer quantity) {
		super();
		this.orderedId = ordered_id;
		this.itemId = item_id;
		this.quantity = quantity;
	}

	public Integer getId() {
		return id;
	}

	public Integer getOrderedId() {
		return orderedId;
	}

	public Integer getItemId() {
		return itemId;
	}

	public Integer getQuantity() {
		return quantity;
	}

}
