package com.luv2code.spring.ExpenseTracker.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.spring.ExpenseTracker.Model.User;
import com.luv2code.spring.ExpenseTracker.Service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		userService=this.userService;
	}
	
	@GetMapping("/users")
	public List<User> findAllUsers() {
		return userService.findAll();
	}
}
