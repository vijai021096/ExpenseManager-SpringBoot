package com.luv2code.spring.ExpenseTracker.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luv2code.spring.ExpenseTracker.Model.Roles;
import com.luv2code.spring.ExpenseTracker.Repository.RoleRepository;

@Service
public class RoleService {

	@Autowired
	private RoleRepository roleRepository;
	
	public Roles findByName(String name) {
		   return roleRepository.findByName(name);
	}
}
