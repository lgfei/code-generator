package com.lgfei.code.generator.common.entity;

import com.lgfei.betterme.framework.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 用户数据源关系表
 * </p>
 *
 * @author lgfei
 * @since 2019-07-29
 */
@ApiModel(value="UserDsRelation对象", description="用户数据源关系表")
public class UserDsRelation extends BaseEntity<Long>
{

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户编码")
    private String userNo;

    @ApiModelProperty(value = "数据源编码")
    private String dsNo;

    @ApiModelProperty(value = "允许生成的次数", example = "1")
    private Integer allowNum;

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
    public Integer getAllowNum() {
        return allowNum;
    }

    public void setAllowNum(Integer allowNum) {
        this.allowNum = allowNum;
    }

    @Override
    public String toString() {
        return "UserDsRelation{" +
        "userNo=" + userNo +
        ", dsNo=" + dsNo +
        ", allowNum=" + allowNum +
        "}";
    }
}
