package com.luv2code.spring.ExpenseTracker.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luv2code.spring.ExpenseTracker.Model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
