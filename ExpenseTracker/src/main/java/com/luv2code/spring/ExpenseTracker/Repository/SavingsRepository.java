package com.luv2code.spring.ExpenseTracker.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luv2code.spring.ExpenseTracker.Model.Savings;

public interface SavingsRepository extends JpaRepository<Savings,Integer> {

   List<Savings>findByUserId(int id);
	
   Savings findById(int id);
}
