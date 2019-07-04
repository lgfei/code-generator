package com.lgfei.code.generator.model.vo;

import java.io.Serializable;
import java.util.List;

public class ResultVO<T> implements Serializable{

	private static final long serialVersionUID = 1L;

	private String code = "1";
	
	private String msg = "success";
	
	private T data;
	
	private List<T> list;
	
	private Object obj;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}
	
}
