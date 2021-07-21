package com.nattav.model;

import java.math.BigDecimal;

public class MemberRequest {

	private String username;
	private BigDecimal salary;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

}
