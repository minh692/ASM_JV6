package com.store.service;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.store.entity.Order;

public interface OrderService {
	List<Order> findAll();
	
	Order findById(Long id);
	
	Order create(JsonNode orderData);

	List<Order> findByUserName(String username);
}
