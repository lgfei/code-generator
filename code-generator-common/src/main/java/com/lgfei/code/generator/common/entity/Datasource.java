package com.lgfei.code.generator.common.entity;

import com.lgfei.betterme.framework.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 数据源信息表
 * </p>
 *
 * @author lgfei
 * @since 2019-07-29
 */
@ApiModel(value="Datasource对象", description="数据源信息表")
public class Datasource extends BaseEntity<Long>
{

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "数据源编码")
    private String dsNo;

    @ApiModelProperty(value = "数据源名称")
    private String name;

    @ApiModelProperty(value = "数据库类型，mysql,oracle")
    private String dbType;

    @ApiModelProperty(value = "数据源类类型")
    private String type;

    @ApiModelProperty(value = "数据驱动")
    private String driver;

    @ApiModelProperty(value = "数据库ip")
    private String server;

    @ApiModelProperty(value = "端口", example = "1")
    private Integer port;

    @ApiModelProperty(value = "数据库名称")
    private String schemaName;

    @ApiModelProperty(value = "数据库用户名")
    private String username;

    @ApiModelProperty(value = "数据库用户密码")
    private String password;

    public String getDsNo() {
        return dsNo;
    }

    public void setDsNo(String dsNo) {
        this.dsNo = dsNo;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }
    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }
    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
    public String getSchemaName() {
        return schemaName;
    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Datasource{" +
        "dsNo=" + dsNo +
        ", name=" + name +
        ", dbType=" + dbType +
        ", type=" + type +
        ", driver=" + driver +
        ", server=" + server +
        ", port=" + port +
        ", schemaName=" + schemaName +
        ", username=" + username +
        ", password=" + password +
        "}";
    }
}
