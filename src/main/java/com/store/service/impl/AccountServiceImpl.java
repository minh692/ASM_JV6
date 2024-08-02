package com.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.dao.AccountDAO;
import com.store.entity.Account;
import com.store.service.AccountService;
@Service
public class AccountServiceImpl implements AccountService{
	@Autowired
	AccountDAO dao;
	
	@Override
	public Account findById(String username) {
		return dao.findById(username).get();
	}

	@Override
	public List<Account> findAll() {
		return dao.findAll();
	}

	@Override
	public List<Account> getAdministrators() {
		// TODO Auto-generated method stub
		return dao.getAdministrators();
	}

}
