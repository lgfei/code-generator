package com.lgfei.code.generator.common.entity;

import com.lgfei.betterme.framework.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 生成日志表
 * </p>
 *
 * @author lgfei
 * @since 2019-07-29
 */
@ApiModel(value="LogGenerate对象", description="生成日志表")
public class LogGenerate extends BaseEntity<Long>
{

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "日志编码")
    private String logNo;

    @ApiModelProperty(value = "日志名称")
    private String name;

    @ApiModelProperty(value = "数据源编码")
    private String dsNo;

    @ApiModelProperty(value = "用户编码")
    private String userNo;

    @ApiModelProperty(value = "详细内容")
    private String content;

    public String getLogNo() {
        return logNo;
    }

    public void setLogNo(String logNo) {
        this.logNo = logNo;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getDsNo() {
        return dsNo;
    }

    public void setDsNo(String dsNo) {
        this.dsNo = dsNo;
    }
    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "LogGenerate{" +
        "logNo=" + logNo +
        ", name=" + name +
        ", dsNo=" + dsNo +
        ", userNo=" + userNo +
        ", content=" + content +
        "}";
    }
}
