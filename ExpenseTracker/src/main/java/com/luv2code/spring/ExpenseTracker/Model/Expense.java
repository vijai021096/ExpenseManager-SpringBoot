package com.luv2code.spring.ExpenseTracker.Model;

import java.sql.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Expense")
public class Expense {

	@Id
	@Column(name="expense_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int expenseId;
	
	@Column(name = "user_expense_id")
	private int userExpenseId;
	
	@Column(name="expense_category_id")
	private int expenseCategoryId;
	
	@Column(name ="expense_amount")
	private int amount;
	
	@Column(name="expense_date")
	private Date expenseDate;
	
	@Column(name="expense_added_date")
	private Date expenseAddedDate;
	
	@Column(name="expense_notes")
	private String expenseNotes;


	public Expense(int expenseId, int userExpenseId, int expenseCategoryId, int amount, Date expenseDate,
			Date expenseAddedDate, String expenseNotes) {
		super();
		this.expenseId = expenseId;
		this.userExpenseId = userExpenseId;
		this.expenseCategoryId = expenseCategoryId;
		this.amount = amount;
		this.expenseDate = expenseDate;
		this.expenseAddedDate = expenseAddedDate;
		this.expenseNotes = expenseNotes;
	}

	public Expense() {
		super();
	}

	public int getExpenseId() {
		return expenseId;
	}

	public void setExpenseId(int expenseId) {
		this.expenseId = expenseId;
	}

	public int getUserExpenseId() {
		return userExpenseId;
	}

	public void setUserExpenseId(int userExpenseId) {
		this.userExpenseId = userExpenseId;
	}

	public int getExpenseCategoryId() {
		return expenseCategoryId;
	}

	public void setExpenseCategoryId(int expenseCategoryId) {
		this.expenseCategoryId = expenseCategoryId;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Date getExpenseDate() {
		return expenseDate;
	}

	public void setExpenseDate(Date expenseDate) {
		this.expenseDate = expenseDate;
	}

	public Date getExpenseAddedDate() {
		return expenseAddedDate;
	}

	public void setExpenseAddedDate(Date expenseAddedDate) {
		this.expenseAddedDate = expenseAddedDate;
	}

	public String getExpenseNotes() {
		return expenseNotes;
	}

	public void setExpenseNotes(String expenseNotes) {
		this.expenseNotes = expenseNotes;
	}

	@Override
	public String toString() {
		return "Expense [expenseId=" + expenseId + ", userExpenseId=" + userExpenseId + ", expenseCategoryId="
				+ expenseCategoryId + ", amount=" + amount + ", expenseDate=" + expenseDate + ", expenseAddedDate="
				+ expenseAddedDate + ", expenseNotes=" + expenseNotes + "]";
	}
	
	
	
}
