package com.lgfei.code.generator.common.entity;

import com.lgfei.betterme.framework.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 用户模块关系表
 * </p>
 *
 * @author lgfei
 * @since 2019-07-31
 */
@ApiModel(value="UserModuleOperation对象", description="用户模块关系表")
public class UserModuleOperation extends BaseEntity<Long>
{

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户编码")
    private String userNo;

    @ApiModelProperty(value = "模块编码")
    private String moduleNo;

    @ApiModelProperty(value = "按钮权限值(多个按逗号,隔开)")
    private String operations;

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }
    public String getModuleNo() {
        return moduleNo;
    }

    public void setModuleNo(String moduleNo) {
        this.moduleNo = moduleNo;
    }
    public String getOperations() {
        return operations;
    }

    public void setOperations(String operations) {
        this.operations = operations;
    }

    @Override
    public String toString() {
        return "UserModuleOperation{" +
        "userNo=" + userNo +
        ", moduleNo=" + moduleNo +
        ", operations=" + operations +
        "}";
    }
}
