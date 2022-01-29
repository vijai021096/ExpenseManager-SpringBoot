package com.luv2code.spring.ExpenseTracker.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luv2code.spring.ExpenseTracker.Model.Savings;
import com.luv2code.spring.ExpenseTracker.Repository.SavingsRepository;

@Service
public class SavingsService {

	@Autowired
	private SavingsRepository savingsRepository;
	
	public void saveSavings(Savings savings) {
		savingsRepository.save(savings);
	}

	public List<Savings> findByUserId(int id) {
		// TODO Auto-generated method stub
		return savingsRepository.findByUserId(id);
	}
}
