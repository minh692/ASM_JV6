package com.store.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.store.dao.OrderDAO;
import com.store.dao.OrderDetailDAO;
import com.store.entity.Order;
import com.store.entity.OrderDetail;
import com.store.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{
	@Autowired
	OrderDAO orderDAO;
	
	@Autowired
	OrderDetailDAO orderDetailDAO;
	
	@Override
	public List<Order> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order findById(Long id) {
		return orderDAO.findById(id).get();
	}
	
	@Override
	public Order create(JsonNode orderData) {
		ObjectMapper mapper = new ObjectMapper();
		
		try {
		    Order order = mapper.convertValue(orderData, Order.class);
		    orderDAO.save(order);
		    
		    TypeReference<List<OrderDetail>> type = new TypeReference<List<OrderDetail>>() {};
		    List<OrderDetail> details = mapper.convertValue(orderData.get("orderDetails"), type)
		            .stream().peek(d -> d.setOrder(order)).collect(Collectors.toList());
		    orderDetailDAO.saveAll(details);
		    
		    return order;
		} catch (Exception e) {
		    e.printStackTrace();
		    // Có thể thêm log hoặc gửi thông báo lỗi chi tiết về phía client
		    throw new RuntimeException("Error processing order", e);
		}
	}

	@Override
	public List<Order> findByUserName(String username) {
		
		return orderDAO.findByUserName(username);
	}

}
