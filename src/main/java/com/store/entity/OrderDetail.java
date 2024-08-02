package com.store.entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "Order_Details")
public class OrderDetail implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	Double price;
	
	Integer quantity;
	
	@ManyToOne 
	@JoinColumn(name = "Orderid")
	Order order;
	
	@ManyToOne
	@JoinColumn(name = "Productid")
	Product product;
	
}
