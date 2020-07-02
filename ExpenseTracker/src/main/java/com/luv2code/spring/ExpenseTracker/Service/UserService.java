package com.luv2code.spring.ExpenseTracker.Service;

import java.util.List;

import com.luv2code.spring.ExpenseTracker.Model.Category;
import com.luv2code.spring.ExpenseTracker.Model.Expense;
import com.luv2code.spring.ExpenseTracker.Model.User;

public interface UserService {
	
	public List<User> findAll();
	
	public List<Category>findAllCategory();

	public List<Expense>findAllExpense();
	
	public List<Expense>findByUserId(int id);
	
	public List<Expense> findByCategoryId(int id);
	
	public List<Expense> findByUserAndCategory(int categoryId,int userId);
}
