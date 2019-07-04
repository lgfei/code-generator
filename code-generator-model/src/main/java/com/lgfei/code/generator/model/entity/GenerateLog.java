package com.lgfei.code.generator.model.entity;

public class GenerateLog extends BaseEntity{

	private static final long serialVersionUID = 1L;

	private String logNo;
	
	private String name;
	
	private String dsNo;
	
	private String userNo;
	
	private String content;
	
	private Integer status;

	public String getLogNo() {
		return logNo;
	}

	public void setLogNo(String logNo) {
		this.logNo = logNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDsNo() {
		return dsNo;
	}

	public void setDsNo(String dsNo) {
		this.dsNo = dsNo;
	}

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
