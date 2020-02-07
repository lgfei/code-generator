package com.lgfei.code.generator.common.entity;

import com.lgfei.betterme.framework.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 登录日志表
 * </p>
 *
 * @author lgfei
 * @since 2019-07-31
 */
@ApiModel(value="LogLogin对象", description="登录日志表")
public class LogLogin extends BaseEntity<Long>
{

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "日志编码")
    private String logNo;

    @ApiModelProperty(value = "用户编码")
    private String userNo;

    @ApiModelProperty(value = "客户端IP")
    private String clientIp;

    public String getLogNo() {
        return logNo;
    }

    public void setLogNo(String logNo) {
        this.logNo = logNo;
    }
    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }
    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    @Override
    public String toString() {
        return "LogLogin{" +
        "logNo=" + logNo +
        ", userNo=" + userNo +
        ", clientIp=" + clientIp +
        "}";
    }
}
