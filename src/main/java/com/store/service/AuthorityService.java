package com.store.service;

import java.util.List;

import com.store.entity.Authority;

public interface AuthorityService {
	public List<Authority> findAll() ;

	public Authority create(Authority auth) ;

	public void delete(Integer id) ;

	public List<Authority> findAuthoritiesOfAdministrators() ;
}
