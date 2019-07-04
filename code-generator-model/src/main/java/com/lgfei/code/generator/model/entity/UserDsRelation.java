package com.lgfei.code.generator.model.entity;

public class UserDsRelation  extends BaseEntity{

	private static final long serialVersionUID = 1L;

	private String userNo;
	
	private String dsNo;

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public String getDsNo() {
		return dsNo;
	}

	public void setDsNo(String dsNo) {
		this.dsNo = dsNo;
	}
	
}
