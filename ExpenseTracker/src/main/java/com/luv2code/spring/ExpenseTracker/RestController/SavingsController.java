package com.luv2code.spring.ExpenseTracker.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.luv2code.spring.ExpenseTracker.Model.Savings;
import com.luv2code.spring.ExpenseTracker.Model.User;
import com.luv2code.spring.ExpenseTracker.Service.SavingsService;
import com.luv2code.spring.ExpenseTracker.Service.UserService;

@Controller
public class SavingsController {

	@Autowired
	private UserService userService;

	@Autowired
	private SavingsService savingsService;

	// save Savings
	@PostMapping("/saveSavings")
	public String saveSavings(@ModelAttribute("savings") Savings theSavings) {

		// save the employee
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findByUserName(auth.getName()).get(0);
		theSavings.setUser(user);

		savingsService.saveSavings(theSavings);

		// use a redirect to prevent duplicate submissions
		return "redirect:/incomes";
	}

	// Add savings
	@GetMapping("/showFormForAddSavings")
	public String showFormForAdd(Model theModel) {

		// create model attribute to bind form data
		Savings theSavings = new Savings();
		theModel.addAttribute("savings", theSavings);

		return "add-savings";
	}
	
	// List expenses for user
		@GetMapping("/savings")
		public String findAllExpesnes(Model theModel) {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User user = userService.findByUserName(auth.getName()).get(0);
			
			List<Savings> savings = savingsService.findByUserId(user.getId());
			int totalSavings = 0;
			for (Savings sum : savings) {
				totalSavings += sum.getAmount();
			}
			
		theModel.addAttribute("savings", savings);
		theModel.addAttribute("totalAmount", totalSavings);
			return "list-savings";
		}

}
