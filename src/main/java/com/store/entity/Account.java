package com.store.entity;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
@SuppressWarnings("serial") // ngăn chặn cảnh báo của trình biên dịch Serializable
@Data
@Entity
@Table(name = "Accounts")
public class Account implements Serializable{
	@Id
	String username;
	
	String password;
	
	String fullname;
	
	String email;
	
	String photo;
	
	@JsonIgnore
	@OneToMany(mappedBy = "account")
	List<Order> orders;
	
	@JsonIgnore
	@OneToMany(mappedBy = "account", fetch = FetchType.EAGER) // FetchType tải về các dữ liệu authority khi account được truy vấn
	List<Authority> authorities;								// EAGER: nạp đầy đủ
	
}
