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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.luv2code.spring.ExpenseTracker.Model.Category;

import com.luv2code.spring.ExpenseTracker.Model.Expense;
import com.luv2code.spring.ExpenseTracker.Model.User;

import com.luv2code.spring.ExpenseTracker.Service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	public UserController(UserService userService) {
	  this.userService=userService;
	}
    
	@GetMapping("/users")
	public String findAllUsers(Model theModel) {
		
		List<User> usersList = userService.findAll();
		theModel.addAttribute("USER", usersList);
		return "list-users";
	}
	
	@GetMapping("/category")
	public String findAllCategories(Model theModel) {
		
		List<Category> categories = userService.findAllCategory();
		theModel.addAttribute("categoriesList", categories);
		
		return "list-categories";
	}
	
	@GetMapping("/expenses")
	public String findAllExpesnes(Model theModel) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user= userService.findByUserName(auth.getName()).get(0);
		List<Expense> expenses = userService.findByUserId(user.getId());
		theModel.addAttribute("Expenses", expenses);
		return "list-expenses";
	}
	
	/*@GetMapping("/expenseById")
	public String findExpesnesByUserId(Model theModel,@RequestParam("userId") int id) {
		List<Expense> expenses = userService.findByUserId(id);
		theModel.addAttribute("ExpensesByUser", expenses);
		return "list-expenses-User";
	}*/
	
	@GetMapping("/expenseByCatId")
	public String findExpesnesByUserId(@ModelAttribute("categoryId") int id,Model theModel) {
		List<Expense> expenses = userService.findByCategoryId(id);
		theModel.addAttribute("ExpensesByUser", expenses);
		return "list-expenses-User";
	}
	

	
	
	
	@GetMapping("/home")
	public String showHomePage() {
		 return "redirect:/expenses";
	}
	
@GetMapping("/userName")
public String showUsersByName(Model model,@RequestParam("userName")String userName) {
	List<User> user=userService.findByUserName(userName);
	model.addAttribute("userName", user);
	return "user-by-username";
}

@GetMapping("/showFormForAdd")
public String showFormForAdd(Model theModel) {
	
	// create model attribute to bind form data
	Expense theExpense = new Expense();
	
	List<Category> theCategory = userService.findAllCategory();

	
	theModel.addAttribute("expense", theExpense);
	theModel.addAttribute("category", theCategory);
	
	return "add-expense";
}
@PostMapping("/save")
public String saveEmployee(@ModelAttribute("expense") Expense theExpenses) {
	
	// save the employee
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	User user= userService.findByUserName(auth.getName()).get(0);
	theExpenses.setUser(user);
	theExpenses.setExpenseAddedDate(new java.sql.Date(new Date().getTime()));
	userService.saveExpense(theExpenses);
	
	// use a redirect to prevent duplicate submissions
	return "redirect:/expenses";
}
	
	@GetMapping("/redirect")
	public String redirectToLogin() {
		return "redirect:register";
	}
	
	
	 
}
