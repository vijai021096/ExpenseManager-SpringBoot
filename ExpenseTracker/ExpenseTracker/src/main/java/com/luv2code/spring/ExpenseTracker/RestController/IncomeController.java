package com.luv2code.spring.ExpenseTracker.RestController;


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


import com.luv2code.spring.ExpenseTracker.Model.Income;
import com.luv2code.spring.ExpenseTracker.Model.User;
import com.luv2code.spring.ExpenseTracker.Service.IncomeService;
import com.luv2code.spring.ExpenseTracker.Service.UserService;

@Controller
public class IncomeController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private IncomeService incomeService;
	
	//List Incomes for user
		@GetMapping("/incomes")
		public String findAllIncomes(Model theModel) {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User user= userService.findByUserName(auth.getName()).get(0);
			List<Income> incomes = incomeService.findByUserId(user.getId());
			int sumAmount=0;
			for(Income sum:incomes) {
				sumAmount+=sum.getAmount();
			}
			theModel.addAttribute("totalAmount", sumAmount);
			theModel.addAttribute("Incomes", incomes);
			return "list-incomes";
		}
		
		//Add income
		@GetMapping("/showFormForAddIncome")
		public String showFormForAdd(Model theModel) {
			
			// create model attribute to bind form data
			Income theIncome = new Income();	
			theModel.addAttribute("income", theIncome);
			
			
			return "add-income";
		}
		
		//save expense
		@PostMapping("/saveIncome")
		public String saveIncome(@ModelAttribute("income") Income theIncome) {
			
			// save the employee
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User user= userService.findByUserName(auth.getName()).get(0);
			theIncome.setUser(user);
			
			incomeService.save(theIncome);
			
			// use a redirect to prevent duplicate submissions
			return "redirect:/incomes";
		}
			
		//Update Income
		@GetMapping("/showFormForUpdateIncomes/{incomeId}")
		public String showFormForUpdate( @PathVariable("incomeId") int theIncome,
										Model theModel) {
			
			// get the employee from the service
			Income income =incomeService.findById(theIncome);
			
			// set employee as a model attribute to pre-populate the form
			theModel.addAttribute("income", income);
			
			return "add-income";			
		}
		
		//Delete Income
		@GetMapping("/deleteIncome/{incomeId}")
		public String delete( @PathVariable("incomeId") int theIncome,
										Model theModel) {
			incomeService.deleteById(theIncome);
			return "redirect:/incomes";
		}
}
