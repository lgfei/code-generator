package com.lgfei.code.generator.model.vo;

import java.io.Serializable;

public class RequestVO<T> implements Serializable{

	private static final long serialVersionUID = 1L;

	private T entity;
	
	private int page = 1;
	
	private int limit = 10;

	public T getEntity() {
		return entity;
	}

	public void setEntity(T entity) {
		this.entity = entity;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}
	
}
