package com.nattav.model;

import java.math.BigDecimal;

public class MemberResponse {

	private String username;
	private BigDecimal salary;
	private String member_level;

	public String getMember_level() {
		return member_level;
	}

	public void setMember_level(String member_level) {
		this.member_level = member_level;
	}

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
