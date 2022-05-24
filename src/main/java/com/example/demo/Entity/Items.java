package com.example.demo.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "items")
public class Items {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "price")
	private Integer price;

	@Column(name = "stock")
	private Integer stock;

	@Column(name = "image")
	private String image;

	@Column(name = "name")
	private String name;

	@Column(name = "delivery_days")
	private Integer delivery_days;

	public Items() {
		super();
	}

	public Items(Integer id, Integer price, Integer stock, String image, String name, Integer delivery_days,
			Integer quantity) {
		super();
		this.id = id;
		this.price = price;
		this.stock = stock;
		this.image = image;
		this.name = name;
		this.delivery_days = delivery_days;
		this.quantity = quantity;
	}

	public Items(Integer price, Integer stock, String image, String name) {
		super();
		this.price = price;
		this.stock = stock;
		this.image = image;
		this.name = name;
	}

	@Transient
	private Integer quantity;

	public Integer getId() {
		return id;
	}

	public Integer getPrice() {
		return price;
	}

	public Integer getStock() {
		return stock;
	}

	public String getImage() {
		return image;
	}

	public String getName() {
		return name;
	}

	public Integer getDelivery_days() {
		return delivery_days;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}
