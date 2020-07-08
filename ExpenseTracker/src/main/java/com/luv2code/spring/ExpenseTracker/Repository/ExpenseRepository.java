package com.luv2code.spring.ExpenseTracker.Repository;


import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import org.springframework.data.rest.core.annotation.RestResource;

import com.luv2code.spring.ExpenseTracker.Model.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Integer> {

	List<Expense> findByCategoryCategoryId(int id);
	
	List<Expense>findByUserId(int id);
	
	Expense findByExpenseId(int id);
	
	
	
}
