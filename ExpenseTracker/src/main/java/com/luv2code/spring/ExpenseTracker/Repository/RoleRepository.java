package com.luv2code.spring.ExpenseTracker.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luv2code.spring.ExpenseTracker.Model.Roles;

public interface RoleRepository extends JpaRepository<Roles, Integer> {
 
	 Roles findByName(String name);
}
