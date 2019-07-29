package com.lgfei.code.generator.common.entity;

import com.lgfei.betterme.framework.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author lgfei
 * @since 2019-07-29
 */
@ApiModel(value="SysUser对象", description="用户信息表")
public class SysUser extends BaseEntity<Long>
{

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户编码")
    private String userNo;

    @ApiModelProperty(value = "登录帐号")
    private String account;

    @ApiModelProperty(value = "用户名称")
    private String name;

    @ApiModelProperty(value = "用户密码")
    private String password;

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
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

    @Override
    public String toString() {
        return "SysUser{" +
        "userNo=" + userNo +
        ", account=" + account +
        ", name=" + name +
        ", password=" + password +
        "}";
    }
}
