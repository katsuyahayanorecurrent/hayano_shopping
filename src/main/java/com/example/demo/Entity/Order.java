package com.example.demo.Entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ordered")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="user_id")
	private Integer userId;
	
	@Column(name="ordered_date")
	private Date orderedDate;
	
	@Column(name="total_price")
	private Integer totalPrice;
	
	public Order() {
		super();
	}

	public Order(Integer user_id, Date orderedDate, Integer totalPrice) {
		super();
		this.userId = user_id;
		this.orderedDate = orderedDate;
		this.totalPrice = totalPrice;
	}

	public Integer getId() {
		return id;
	}

	public Integer getUserId() {
		return userId;
	}

	public Date getOrderedDate() {
		return orderedDate;
	}

	public Integer getTotalPrice() {
		return totalPrice;
	}


}
