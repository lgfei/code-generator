package com.lgfei.code.generator.common.entity;

import com.lgfei.betterme.framework.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 模块信息表
 * </p>
 *
 * @author lgfei
 * @since 2019-07-29
 */
@ApiModel(value="Module对象", description="模块信息表")
public class Module extends BaseEntity<Long>
{

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "模块编码")
    private String moduleNo;

    @ApiModelProperty(value = "模块名称")
    private String name;

    @ApiModelProperty(value = "入口地址")
    private String url;

    public String getModuleNo() {
        return moduleNo;
    }

    public void setModuleNo(String moduleNo) {
        this.moduleNo = moduleNo;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Module{" +
        "moduleNo=" + moduleNo +
        ", name=" + name +
        ", url=" + url +
        "}";
    }
}
