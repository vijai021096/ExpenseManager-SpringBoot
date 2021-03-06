package com.luv2code.spring.ExpenseTracker.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

//import com.luv2code.spring.ExpenseTracker.Model.Category;
import com.luv2code.spring.ExpenseTracker.Model.ConfirmationToken;
import com.luv2code.spring.ExpenseTracker.Model.Expense;
import com.luv2code.spring.ExpenseTracker.Model.User;
//import com.luv2code.spring.ExpenseTracker.Repository.CategoryRepository;
import com.luv2code.spring.ExpenseTracker.Repository.ConfirmationTokenRepository;
import com.luv2code.spring.ExpenseTracker.Repository.ExpenseRepository;
import com.luv2code.spring.ExpenseTracker.Repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	/*@Autowired
	private CategoryRepository categoryRepository;*/
	
	@Autowired
	private ExpenseRepository expenseRepository;
	
	@Autowired
	private ConfirmationTokenRepository confirmationRepository;
	
	 public UserServiceImpl(UserRepository theUserRepository ) {
		 userRepository=theUserRepository;
	}
	@Override
	@Transactional
	
	public List<User> findAll() {
		
		return userRepository.findAll();
		
	}
	/*@Override
	@Transactional
	public List<Category> findAllCategory() {
		
		return categoryRepository.findAll();
	}*/
	@Override
	@Transactional
	public List<Expense> findAllExpense() {
		
		return expenseRepository.findAll();
	}
	@Override
	@Transactional
	public List<Expense> findByUserId(int id) {
		
		return expenseRepository.findByUserId(id);
	}
	

	@Override
	@Transactional
	public List<User> findByEmailIdIgnoreCase(String emailId) {
		
		return userRepository.findByEmailIdIgnoreCase(emailId);
	}
	@Override
	@Transactional
	public ConfirmationToken findByConfirmationToken(String confirmationToken) {
		
		return confirmationRepository.findByConfirmationToken(confirmationToken);
	}
	@Override
	@Transactional
	public void save(User user) {
		
		userRepository.save(user);
		
	}
	@Override
	@Transactional
	public void saveConfirmation(ConfirmationToken confirmationToken) {
		
		confirmationRepository.save(confirmationToken);
		
	}
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<User> users = userRepository.findByUserName(username);
        
        if (CollectionUtils.isEmpty(users)) {
            throw new UsernameNotFoundException("Could not find user");
        }
        User user = users.get(0);
        return new org.springframework.security.core.userdetails.User(users.get(0).getUserName(), user.getPassword(),user.isEnabled(),true,true,true, new ArrayList<>());


	}
	@Override
	@Transactional
	public List<User> findByUserName(String userName) {
		// check the database if the user already exists
		return userRepository.findByUserName(userName);
	}
	@Override
	public User findById(int id) {
		
		return userRepository.findById(id);
	}
	@Override
	public void saveExpense(Expense expense) {
		
	   expenseRepository.save(expense);
		
	}
	/*@Override
	public List<Expense> findByCategoryId(int id) {
		return expenseRepository.findByCategoryCategoryId(id);
	}*/
	@Override
	public Expense findByExpenseId(int id) {
		Expense expense = expenseRepository.findByExpenseId(id);
		Expense theExpense =null;
		if(expense!=null) {
			theExpense=expense;
		}
		else {
			throw new RuntimeException("No Expense found for"+id);
		}
		return theExpense;
	}

}
