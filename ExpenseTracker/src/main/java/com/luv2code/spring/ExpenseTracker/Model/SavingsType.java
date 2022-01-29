package com.luv2code.spring.ExpenseTracker.Model;

public enum SavingsType {
	FIXED_DEPOSIT("fixed_deposit"), RECURRING_DEPOSIT("recurring_deposit"), HDFC_MUTUAL_FUND("hdfc_mutual_fund"), SBI_MUTUAL_FUND("sbi_mutual_fund"), ADITYA_BIRLA_MUTUAL_FUND("aditya_birla_mutual_fund");
	
	private String type;

	public String getType() {
		return type;
	}

	SavingsType(String type) {
		this.type=type;
	}
	
	
}
