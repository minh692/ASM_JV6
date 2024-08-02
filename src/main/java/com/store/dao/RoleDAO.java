package com.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.entity.Role;

public interface RoleDAO extends JpaRepository<Role, String>{

}
