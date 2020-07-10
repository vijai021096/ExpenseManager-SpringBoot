package com.luv2code.spring.ExpenseTracker.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luv2code.spring.ExpenseTracker.Model.Income;
import com.luv2code.spring.ExpenseTracker.Repository.IncomeRepository;

@Service
public class IncomeService {

	@Autowired
	private IncomeRepository incomeRepository;
	
	//Income for users
	public List<Income> findByUserId(int id){
		
		return incomeRepository.findByUserId(id);
	}
	
	//income by id
	public Income findById(int id) {
		Optional<Income> income = incomeRepository.findById(id);
		Income theIncome =  null;
		if(income.isPresent()) {
			theIncome=income.get();
		}
		else {
			throw new RuntimeException("No Income present for id"+id);
		}
		return theIncome;
	}
	
	//Delete Income
	public void deleteById(int id) {
		Optional<Income> income = incomeRepository.findById(id);
		if(income.isPresent()) {
			incomeRepository.deleteById(id);
		}
		else {
			throw new RuntimeException("No Income present for id"+id);
		}
	}
	
	//Delete income for User
	public void deleteByUserId(int id) {
		List<Income> income = incomeRepository.findByUserId(id);
		if(!income.isEmpty()) {
			incomeRepository.deleteByUserId(id);
		}
		else {
			throw new RuntimeException("No Income found for User");
		}
	}
	
	//save
	public void save(Income income) {
		incomeRepository.save(income);
	}
}
