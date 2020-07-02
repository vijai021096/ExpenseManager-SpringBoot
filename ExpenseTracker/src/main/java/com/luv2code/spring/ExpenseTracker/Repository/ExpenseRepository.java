package com.luv2code.spring.ExpenseTracker.Repository;


import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import org.springframework.data.rest.core.annotation.RestResource;

import com.luv2code.spring.ExpenseTracker.Model.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Integer> {

	 @RestResource(path="userid")                               
		List<Expense> findByUserExpenseId(@Param("id")int id);   
	 
	@RestResource(path="categoryid")
		List<Expense> findByExpenseCategoryId(@Param("id")int id); 
	
	@RestResource(path="categoryUserid")
	List<Expense> findByExpenseCategoryIdAndUserExpenseId(@Param("userId")int categoryId,@Param("categoryId")int userId); 
}
