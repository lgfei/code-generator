package com.lgfei.code.generator.common.entity;

import com.lgfei.betterme.framework.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 按钮信息表
 * </p>
 *
 * @author lgfei
 * @since 2019-07-31
 */
@ApiModel(value="Operation对象", description="按钮信息表")
public class Operation extends BaseEntity<Long>
{

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "按钮编码")
    private String operationNo;

    @ApiModelProperty(value = "按钮名称")
    private String name;

    @ApiModelProperty(value = "权限值")
    private String value;

    public String getOperationNo() {
        return operationNo;
    }

    public void setOperationNo(String operationNo) {
        this.operationNo = operationNo;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Operation{" +
        "operationNo=" + operationNo +
        ", name=" + name +
        ", value=" + value +
        "}";
    }
}
