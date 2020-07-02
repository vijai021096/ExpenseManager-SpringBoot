package com.luv2code.spring.ExpenseTracker.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.spring.ExpenseTracker.Model.Category;
import com.luv2code.spring.ExpenseTracker.Model.Expense;
import com.luv2code.spring.ExpenseTracker.Model.User;
import com.luv2code.spring.ExpenseTracker.Service.UserService;

@Controller
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserService userService;
	

	public UserController(UserService userService) {
		userService=this.userService;
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
		List<Expense> expenses = userService.findAllExpense();
		theModel.addAttribute("Expenses", expenses);
		return "list-expenses";
	}
	
	@GetMapping("/expenseById")
	public String findExpesnesByUserId(Model theModel,@RequestParam("userId") int id) {
		List<Expense> expenses = userService.findByUserId(id);
		theModel.addAttribute("ExpensesByUser", expenses);
		return "list-expenses-User";
	}
	
	@GetMapping("/expenseByCategory")
	public String findExpenseByCategoryId(Model theModel,@RequestParam("categoryId")int id) {
		List<Expense> expenses = userService.findByCategoryId(id);
		theModel.addAttribute("ExpensesByCategory", expenses);
		return "list-expenses-Category";
	}
	
	@GetMapping("/expenseByUserAndCategory")
	public String findExpenseByUserIdAndCategoryId(Model theModel,@RequestParam("categoryId")int categoryId,@RequestParam("userId") int userId) {
		List<Expense> expenses = userService.findByUserAndCategory(categoryId, userId);
		theModel.addAttribute("ExpensesByUserAndCategory", expenses);
		return "list-expenses-userCategory";
	}
}
