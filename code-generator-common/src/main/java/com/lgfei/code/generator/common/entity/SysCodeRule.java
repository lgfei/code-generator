package com.lgfei.code.generator.common.entity;

import com.lgfei.betterme.framework.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 编码规则表
 * </p>
 *
 * @author lgfei
 * @since 2019-08-25
 */
@ApiModel(value="SysCodeRule对象", description="编码规则表")
public class SysCodeRule extends BaseEntity<Long>
{

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "编码规则序号")
    private Integer ruleSeqNo;
    
    @ApiModelProperty(value = "表名")
    private String noTable;

    @ApiModelProperty(value = "编码字段名")
    private String noField;

    @ApiModelProperty(value = "编码前缀")
    private String noPrefix;

    @ApiModelProperty(value = "编码长度", example = "1")
    private Integer noLength;

    @ApiModelProperty(value = "最小的序列号", example = "1")
    private Integer minSerial;

    @ApiModelProperty(value = "最大的序列号", example = "1")
    private Integer maxSerial;

    @ApiModelProperty(value = "当前的序列号", example = "1")
    private Integer currSerial;

    public Integer getRuleSeqNo() {
        return ruleSeqNo;
    }

    public void setRuleSeqNo(Integer ruleSeqNo) {
        this.ruleSeqNo = ruleSeqNo;
    }

    public String getNoTable() {
        return noTable;
    }

    public void setNoTable(String noTable) {
        this.noTable = noTable;
    }
    public String getNoField() {
        return noField;
    }

    public void setNoField(String noField) {
        this.noField = noField;
    }
    public String getNoPrefix() {
        return noPrefix;
    }

    public void setNoPrefix(String noPrefix) {
        this.noPrefix = noPrefix;
    }
    public Integer getNoLength() {
        return noLength;
    }

    public void setNoLength(Integer noLength) {
        this.noLength = noLength;
    }
    public Integer getMinSerial() {
        return minSerial;
    }

    public void setMinSerial(Integer minSerial) {
        this.minSerial = minSerial;
    }
    public Integer getMaxSerial() {
        return maxSerial;
    }

    public void setMaxSerial(Integer maxSerial) {
        this.maxSerial = maxSerial;
    }
    public Integer getCurrSerial() {
        return currSerial;
    }

    public void setCurrSerial(Integer currSerial) {
        this.currSerial = currSerial;
    }

    @Override
    public String toString() {
        return "SysCodeRule{" +
        "noTable=" + noTable +
        ", noField=" + noField +
        ", noPrefix=" + noPrefix +
        ", noLength=" + noLength +
        ", minSerial=" + minSerial +
        ", maxSerial=" + maxSerial +
        ", currSerial=" + currSerial +
        "}";
    }
}
