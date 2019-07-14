package com.lgfei.code.generator.model.entity;

public class Datasource extends BaseEntity{

	private static final long serialVersionUID = 1L;

	private String dsNo;
	
	private String name;
	
	private String dbType;
	
	private String type;
	
	private String driver;
	
	private String server;
	
	private String schemaName;
	
	private String username;
	
	private String password;

	public String getDsNo() {
		return dsNo;
	}

	public void setDsNo(String dsNo) {
		this.dsNo = dsNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDbType() {
		return dbType;
	}

	public void setDbType(String dbType) {
		this.dbType = dbType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public String getSchemaName() {
		return schemaName;
	}

	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
