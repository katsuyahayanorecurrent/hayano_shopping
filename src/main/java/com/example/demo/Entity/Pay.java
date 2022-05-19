package com.example.demo.Entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="pay")
public class Pay {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="user_id")
	private Integer user_id;
	
	@Column(name="credit_no")
	private Date credit_no;
	
	@Column(name="credit_security")
	private Integer credit_security;

	public Integer getId() {
		return id;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public Date getCredit_no() {
		return credit_no;
	}

	public Integer getCredit_security() {
		return credit_security;
	}


}
