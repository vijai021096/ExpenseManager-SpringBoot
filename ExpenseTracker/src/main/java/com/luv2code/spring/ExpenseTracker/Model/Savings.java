package com.luv2code.spring.ExpenseTracker.Model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "savings")
public class Savings {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "savings_amount")
	private int amount;

	@Column(name = "source_of_savings")
	@Enumerated(EnumType.ORDINAL)
	private SavingsType savingsType;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "user_id")
	private User user;

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

	public SavingsType getSavingsType() {
		return savingsType;
	}

	public void setSavingsType(SavingsType savingsType) {
		this.savingsType = savingsType;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Savings() {
		super();
	}

	public Savings(int id, int amount, SavingsType savingsType, User user) {
		super();
		this.id = id;
		this.amount = amount;
		this.savingsType = savingsType;
		this.user = user;
	}

	@Override
	public String toString() {
		return "Savings [id=" + id + ", amount=" + amount + ", savingsType=" + savingsType + ", user=" + user + "]";
	}

}
