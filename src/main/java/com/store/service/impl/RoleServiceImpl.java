package com.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.dao.RoleDAO;
import com.store.entity.Role;
import com.store.service.RoleService;
@Service
public class RoleServiceImpl implements RoleService{
	@Autowired
	RoleDAO dao;
	
	@Override
	public List<Role> findAll() {
		return dao.findAll();
	}
	
}
