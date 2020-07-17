package com.luv2code.spring.ExpenseTracker.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luv2code.spring.ExpenseTracker.Model.Expense;
import com.luv2code.spring.ExpenseTracker.Repository.ExpenseRepository;

@Service
public class ExpenseService {

	@Autowired
	ExpenseRepository expenseRepository;
	
	public Expense findById(int theId) {
		Optional<Expense> expense = expenseRepository.findById(theId);
		Expense theExpense = null;
		if(expense.isPresent()) {
			theExpense = expense.get();
		}
		else {
			throw new RuntimeException("No Expense found for"+theId);
		}
		return theExpense;
	}
	
	public void deleteById(int id) {
		Optional<Expense> expense = expenseRepository.findById(id);
		
		if(expense.isPresent()) {
			expenseRepository.deleteById(id);
		}
		else {
			throw new RuntimeException("No Expense Found For"+id);
		}
		
	}
	public List<Expense> findByUserId(int id) {
		return expenseRepository.findByUserId(id);
	}
}
