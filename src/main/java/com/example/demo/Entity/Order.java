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
	private Integer user_id;
	
	@Column(name="ordered_date")
	private Date ordered_date;
	
	@Column(name="total_price")
	private Integer total_price;

	public Integer getId() {
		return id;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public Date getOrdered_date() {
		return ordered_date;
	}

	public Integer getTotal_price() {
		return total_price;
	}


}
