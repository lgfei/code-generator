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
 * @since 2019-07-31
 */
@ApiModel(value="UserDatasource对象", description="用户数据源关系表")
public class UserDatasource extends BaseEntity<Long>
{

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户编码")
    private String userNo;

    @ApiModelProperty(value = "数据源编码")
    private String datasourceNo;

    @ApiModelProperty(value = "允许生成的次数", example = "1")
    private Integer allowNum;

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }
    public String getDatasourceNo() {
        return datasourceNo;
    }

    public void setDatasourceNo(String datasourceNo) {
        this.datasourceNo = datasourceNo;
    }
    public Integer getAllowNum() {
        return allowNum;
    }

    public void setAllowNum(Integer allowNum) {
        this.allowNum = allowNum;
    }

    @Override
    public String toString() {
        return "UserDatasource{" +
        "userNo=" + userNo +
        ", datasourceNo=" + datasourceNo +
        ", allowNum=" + allowNum +
        "}";
    }
}
