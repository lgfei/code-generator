package com.lgfei.code.generator.model.entity;

public class SysUser extends BaseEntity{

	private static final long serialVersionUID = 1L;

	private String userNo;
	
	private String name;
	
	private String password;
	
	private Integer allowNum;

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getAllowNum() {
		return allowNum;
	}

	public void setAllowNum(Integer allowNum) {
		this.allowNum = allowNum;
	}
	
}
