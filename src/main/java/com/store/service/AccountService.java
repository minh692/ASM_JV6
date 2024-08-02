package com.store.service;

import java.util.List;

import com.store.entity.Account;

public interface AccountService {
	Account findById(String username);
	
	List<Account> findAll();
	
	List<Account> getAdministrators();
}
