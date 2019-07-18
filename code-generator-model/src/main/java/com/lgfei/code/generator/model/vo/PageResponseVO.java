package com.lgfei.code.generator.model.vo;

import java.io.Serializable;
import java.util.List;

public class PageResponseVO<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private String code = "0";

    private String msg = "success";

    private Long count;

    private List<T> data;

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

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

}
