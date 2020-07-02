package com.luv2code.spring.ExpenseTracker.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luv2code.spring.ExpenseTracker.Model.Category;
import com.luv2code.spring.ExpenseTracker.Model.Expense;
import com.luv2code.spring.ExpenseTracker.Model.User;
import com.luv2code.spring.ExpenseTracker.Repository.CategoryRepository;
import com.luv2code.spring.ExpenseTracker.Repository.ExpenseRepository;
import com.luv2code.spring.ExpenseTracker.Repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ExpenseRepository expenseRepository;
	
	 public UserServiceImpl(UserRepository theUserRepository ) {
		 userRepository=theUserRepository;
	}
	@Override
	public List<User> findAll() {
		
		return userRepository.findAll();
		
	}
	@Override
	public List<Category> findAllCategory() {
		
		return categoryRepository.findAll();
	}
	@Override
	public List<Expense> findAllExpense() {
		
		return expenseRepository.findAll();
	}
	@Override
	public List<Expense> findByUserId(int id) {
		
		return expenseRepository.findByUserExpenseId(id);
	}
	@Override
	public List<Expense> findByCategoryId(int id) {
		
		return expenseRepository.findByExpenseCategoryId(id);
	}
	@Override
	public List<Expense> findByUserAndCategory(int categoryId, int userId) {
		
		return expenseRepository.findByExpenseCategoryIdAndUserExpenseId(categoryId, userId);
	}

}
