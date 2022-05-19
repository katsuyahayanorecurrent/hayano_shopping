package com.example.demo.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ordered_detail")
public class OrderDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="ordered_id")
	private Integer ordered_id;
	
	@Column(name="item_id")
	private Integer item_id;
	
	@Column(name="quantity")
	private Integer quantity;

	public Integer getId() {
		return id;
	}

	public Integer getOrdered_id() {
		return ordered_id;
	}

	public Integer getItem_id() {
		return item_id;
	}

	public Integer getQuantity() {
		return quantity;
	}

}
