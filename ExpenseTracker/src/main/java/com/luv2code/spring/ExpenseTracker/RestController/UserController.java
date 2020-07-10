package com.luv2code.spring.ExpenseTracker.RestController;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.luv2code.spring.ExpenseTracker.Model.Category;

import com.luv2code.spring.ExpenseTracker.Model.Expense;

import com.luv2code.spring.ExpenseTracker.Model.User;
import com.luv2code.spring.ExpenseTracker.Service.ExpenseService;

import com.luv2code.spring.ExpenseTracker.Service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	

	
	public UserController(UserService userService) {
	  this.userService=userService;
	}
    
	
	//Get Users 
	
	@GetMapping("/users")
	public String findAllUsers(Model theModel) {
		
		List<User> usersList = userService.findAll();
		theModel.addAttribute("USER", usersList);
		return "list-users";
	}
	
	
	//Get Categories
	@GetMapping("/category")
	public String findAllCategories(Model theModel) {
		
		List<Category> categories = userService.findAllCategory();
		theModel.addAttribute("categoriesList", categories);
		
		return "list-categories";
	}
	
	
	/*@GetMapping("/expenseById")
	public String findExpesnesByUserId(Model theModel,@RequestParam("userId") int id) {
		List<Expense> expenses = userService.findByUserId(id);
		theModel.addAttribute("ExpensesByUser", expenses);
		return "list-expenses-User";
	}*/
	

	
	//Home page
	@GetMapping("/home")
	public String showHomePage() {
		 return "redirect:/expenses";
	}
	
	//get users by user name
	@GetMapping("/userName")
	public String showUsersByName(Model model,@RequestParam("userName")String userName) {
		List<User> user=userService.findByUserName(userName);
		model.addAttribute("userName", user);
		return "user-by-username";
	}

   //redirect to registration page
	@GetMapping("/redirect")
	public String redirectToLogin() {
		return "redirect:register";
	}	
	 
}
