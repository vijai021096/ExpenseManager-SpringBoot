package com.luv2code.spring.ExpenseTracker.Model;






import java.sql.Date;
import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Expense")
public class Expense {

	@Id
	@Column(name="expense_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int expenseId;
		
	@Column(name ="expense_amount")
	private int amount;
	
	@Column(name="expense_date")
	private Date expenseDate;
	
	@Column(name="expense_added_date")
	private Date expenseAddedDate;
	
	@Column(name="expense_notes")
	private String expenseNotes;

	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id")
	private User user;


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="expense_categroy_id")
	private Category category;
	



	
	
	public Expense() {
		super();
	}


	public Expense(int expenseId, int amount, Date expenseDate, Date expenseAddedDate, String expenseNotes, User user,
			Category category) {
		super();
		this.expenseId = expenseId;
		this.amount = amount;
		this.expenseDate = expenseDate;
		this.expenseAddedDate =expenseAddedDate;
		this.expenseNotes = expenseNotes;
		this.user = user;
		this.category = category;
	}

	
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}


	public int getExpenseId() {
		return expenseId;
	}

	public void setExpenseId(int expenseId) {
		this.expenseId = expenseId;
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
		this.expenseAddedDate =  expenseAddedDate;
	}

	public String getExpenseNotes() {
		return expenseNotes;
	}

	public void setExpenseNotes(String expenseNotes) {
		this.expenseNotes = expenseNotes;
	}

    
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
	@Override
	public String toString() {
		return "Expense [expenseId=" + expenseId + ", amount=" + amount + ", expenseDate=" + expenseDate + ", expenseAddedDate="
				+ expenseAddedDate + ", expenseNotes=" + expenseNotes + "]";
	}
	
	
	
}
