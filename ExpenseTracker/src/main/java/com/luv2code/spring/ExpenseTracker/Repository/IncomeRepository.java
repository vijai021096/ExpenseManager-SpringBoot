package com.luv2code.spring.ExpenseTracker.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luv2code.spring.ExpenseTracker.Model.Income;

public interface IncomeRepository extends JpaRepository<Income, Integer> {

	List<Income>findByUserId(int id);
	
	Optional<Income> findById(int id);
	
	void deleteByUserId(int id);
}
