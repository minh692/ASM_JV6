package com.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.dao.CategoryDAO;
import com.store.entity.Category;
import com.store.service.CategoryService;
@Service
public class CategoryServiceImpl implements CategoryService{
	@Autowired
	CategoryDAO dao;

	@Override
	public List<Category> findAll() {
		return dao.findAll();
	}
}
