package com.example.demo.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "user_name")
	private String user_name;

	@Column(name = "address")
	private String address;

	@Column(name = "email")
	private String email;

	@Column(name = "tell")
	private String tell;

	@Column(name = "name")
	private String name;

	@Column(name = "password")
	private String password;

	public Users() {
		super();
	}

	public Users(Integer id, String user_name, String address, String email, String tell, String name,
			String password) {
		super();
		this.id = id;
		this.user_name = user_name;
		this.address = address;
		this.email = email;
		this.tell = tell;
		this.name = name;
		this.password = password;
	}

	public Users(String address, String email, String tell, String name, String password) {
		super();
		this.address = address;
		this.email = email;
		this.tell = tell;
		this.name = name;
		this.password = password;
	}

	public Users(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public Integer getId() {
		return id;
	}

	public String getUser_name() {
		return user_name;
	}

	public String getAddress() {
		return address;
	}

	public String getEmail() {
		return email;
	}

	public String getTell() {
		return tell;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

}
