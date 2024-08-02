package com.store.service;

import java.util.List;
import java.util.Optional;

import com.store.entity.Product;

public interface ProductService {
	List<Product> findAll();
	
	Product findById(Integer id);

	List<Product> findByCategoryId(String cid);

	Product update(Product product);

	Product create(Product product);

	void delete(Integer id);
}
