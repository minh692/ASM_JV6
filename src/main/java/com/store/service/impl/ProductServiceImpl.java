package com.store.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.dao.ProductDAO;
import com.store.entity.Product;
import com.store.service.ProductService;
@Service
public class ProductServiceImpl implements ProductService{
	@Autowired
	ProductDAO dao;

	@Override
	public List<Product> findAll() {
		
		return dao.findAll();
	}

	@Override
	public Product findById(Integer id) {
		return dao.findById(id).get();
	}

	@Override
	public List<Product> findByCategoryId(String cid) {
		return dao.findByCategoryId(cid);
	}
	
	public Product create(Product product) {
		return dao.save(product);
	}

	public Product update(Product product) {
		return dao.save(product);
	}

	public void delete(Integer id) {
		dao.deleteById(id);
	}
}
