package com.luv2code.spring.ExpenseTracker.Service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.luv2code.spring.ExpenseTracker.Model.Category;
import com.luv2code.spring.ExpenseTracker.Model.ConfirmationToken;
import com.luv2code.spring.ExpenseTracker.Model.Expense;
import com.luv2code.spring.ExpenseTracker.Model.User;

public interface UserService extends UserDetailsService {
	
	public List<User> findAll();
	
	public User findById(int id);
	
	public List<Category>findAllCategory();

	public List<Expense>findAllExpense();
	
	public List<Expense>findByUserId(int id);
	
	public List<Expense>findByCategoryId(int id);
	
	public Expense findByExpenseId(int id);
	
	public List<User> findByEmailIdIgnoreCase(String emailId);
	
	public void saveExpense(Expense expense);
	
	public void save(User user);
	
	public void saveConfirmation(ConfirmationToken confirmationToken);
	
	public ConfirmationToken findByConfirmationToken(String confirmationToken);

	public List<User> findByUserName(String userName);
}
