package com.luv2code.spring.ExpenseTracker.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luv2code.spring.ExpenseTracker.Model.ImageModel;

public interface ImageRepository extends JpaRepository<ImageModel, Integer> {

	ImageModel findByExpensesExpenseId(int id);
}
