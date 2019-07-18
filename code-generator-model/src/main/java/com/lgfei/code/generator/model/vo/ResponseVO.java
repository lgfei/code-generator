package com.lgfei.code.generator.model.vo;

import java.io.Serializable;
import java.util.List;

public class ResponseVO<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private String code = "0";

    private String msg = "success";

    private List<T> data;

    private T entity;

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

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

}
