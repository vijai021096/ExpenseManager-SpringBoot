package com.luv2code.spring.ExpenseTracker.RestController;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2code.spring.ExpenseTracker.Model.Expense;
import com.luv2code.spring.ExpenseTracker.Model.User;
import com.luv2code.spring.ExpenseTracker.Service.ExpenseService;
import com.luv2code.spring.ExpenseTracker.Service.UserService;
import com.luv2code.spring.ExpenseTracker.utils.GeneratePdfReport;

@Controller
public class ReportGenerator {
	
	@Autowired
	ExpenseService expenseService;
	@Autowired
	UserService userService;
	
	@GetMapping(value="/pdfReport",produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<InputStreamResource>expenseReport() throws IOException{
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user= userService.findByUserName(auth.getName()).get(0);
		List<Expense> expenseList = userService.findByUserId(user.getId());
		ByteArrayInputStream bis = GeneratePdfReport.expenseReport(expenseList);
		
		HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=Expensereport.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }

	}
	 
	

	    

