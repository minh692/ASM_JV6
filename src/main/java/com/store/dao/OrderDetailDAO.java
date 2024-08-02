package com.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.entity.OrderDetail;

public interface OrderDetailDAO extends JpaRepository<OrderDetail, Long>{

}
