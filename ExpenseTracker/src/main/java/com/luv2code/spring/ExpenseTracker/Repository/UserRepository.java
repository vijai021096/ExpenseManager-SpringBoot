package com.luv2code.spring.ExpenseTracker.Repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.luv2code.spring.ExpenseTracker.Model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	  List<User> findByEmailIdIgnoreCase(String emailId);
	
	  User findById(int id);
	  
	
	 List<User> findByUserName(String userName);
}
