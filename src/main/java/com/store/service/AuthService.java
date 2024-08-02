package com.store.service;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;

@Service("auth")
public class AuthService {
	@Autowired
	HttpServletRequest request;
	
	public Authentication getAuthentication() {
		return (Authentication) request.getUserPrincipal();
	}
	
	public boolean isAuthenticated() {
		return (this.getAuthentication() != null);
	}
	
	public UserDetails getUserDetails() {
		Authentication auth = this.getAuthentication();
		return (UserDetails) auth.getPrincipal();
	}

	public String getUsername() {
		UserDetails user = this.getUserDetails();
		return user.getUsername();
	}
	
	
	public List<String> getRoles(){
	    UserDetails user = this.getUserDetails();
	    return user.getAuthorities().stream()
	        .map(au -> {
	            String authority = au.getAuthority();
	            return authority.startsWith("ROLE_") ? authority.substring(5) : authority;
	        })
	        .toList();
	}
	
	public boolean hasRole(String...roles) {
		return this.isAuthenticated() && 
				Stream.of(roles).anyMatch(role -> this.getRoles().contains(role));
	}
	
	public boolean isDirectors() {
		return this.hasRole("DIRE");
	}
	
	public boolean isStaff() {
		return this.hasRole("STAF");
	}
	
	public boolean isCustomers() {
		return this.hasRole("CUST");
	}
	
	@Override
	public String toString() {
		return this.getUsername() + ": " +  this.getRoles();
	}
}