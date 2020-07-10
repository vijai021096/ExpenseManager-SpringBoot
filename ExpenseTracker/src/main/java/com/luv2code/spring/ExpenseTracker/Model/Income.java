package com.luv2code.spring.ExpenseTracker.Model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Income")
public class Income {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "income_amount")
	private int amount;
	
	@Column(name="source_of_income")
	private String sourceOfIncome;
	
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id")
    private User user;
    
    public Income() {
		
	}


	public Income(int id, int amount, String sourceOfIncome, User user) {
		super();
		this.id = id;
		this.amount = amount;
		this.sourceOfIncome = sourceOfIncome;
		this.user = user;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	

	public String getSourceOfIncome() {
		return sourceOfIncome;
	}


	public void setSourceOfIncome(String sourceOfIncome) {
		this.sourceOfIncome = sourceOfIncome;
	}


	@Override
	public String toString() {
		return "Income [id=" + id + ", amount=" + amount + ", user=" + user + "]";
	}
    
    
}
