package com.luv2code.spring.ExpenseTracker.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luv2code.spring.ExpenseTracker.Model.ConfirmationToken;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Integer> {
	ConfirmationToken findByConfirmationToken(String confirmationToken);
}
