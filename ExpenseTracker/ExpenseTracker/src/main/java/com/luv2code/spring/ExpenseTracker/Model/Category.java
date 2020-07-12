package com.luv2code.spring.ExpenseTracker.Model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name="Category")
public class Category {

	@Id
	@Column(name = "category_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int categoryId;
	
	@Column(name = "categort_name")
	private String categoryName;

@OneToMany(mappedBy = "category")
private List<Expense> expenseList;


	public Category() {
		
	}


	public Category(int categoryId, String categoryName, List<Expense> expenseList) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.expenseList = expenseList;
	}


	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	
	public List<Expense> getExpenseList() {
	return expenseList;
}

public void setExpenseList(List<Expense> expenseList) {
	this.expenseList = expenseList;
}
	public String getCategoryName() {
		return categoryName;
	}

	
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", CategoryName=" + categoryName + "]";
	}
	
	
}
