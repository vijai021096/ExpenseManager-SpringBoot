package com.luv2code.spring.ExpenseTracker.RestController;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.luv2code.spring.ExpenseTracker.Model.Expense;
import com.luv2code.spring.ExpenseTracker.Model.User;
import com.luv2code.spring.ExpenseTracker.Service.UserService;

@Controller
public class ChartController {
	@Controller
	public class GoogleChartsController {
	 
		@Autowired
		UserService userService;
		
	    @GetMapping("/showCharts")
	    public String getPieChart(Model model) {
	        Map<String, Integer> graphData = new TreeMap<>();
	        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User user= userService.findByUserName(auth.getName()).get(0);
			List<Expense> expenseList = userService.findByUserId(user.getId());
	        for(Expense exp:expenseList) {
	        	graphData.put(exp.getCategory(), exp.getAmount());
	        }
	       
	        model.addAttribute("chartData", graphData);
	        return "chart";
	    }
	}
}
