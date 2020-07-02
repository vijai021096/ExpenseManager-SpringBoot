package com.luv2code.spring.ExpenseTracker.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luv2code.spring.ExpenseTracker.Model.User;
import com.luv2code.spring.ExpenseTracker.Repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	 public UserServiceImpl(UserRepository theUserRepository ) {
		 userRepository=theUserRepository;
	}
	@Override
	public List<User> findAll() {
		
		return userRepository.findAll();
		
	}

}
