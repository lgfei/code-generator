package com.lgfei.code.generator.model.vo;

/**
 * 
 * 页面参数对象 <功能详细描述>
 * 
 * @author lgfei
 * @version [版本号, 2019年7月3日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ApiGeneratorParamVO {
    private String dsNo;

    private String isInit;

    private String groupId;

    private String artifactId;

    private String schemaName;

    private String tableNames;

    private String projectPath;

    public String getDsNo() {
        return dsNo;
    }

    public void setDsNo(String dsNo) {
        this.dsNo = dsNo;
    }

    public String getIsInit() {
        return isInit;
    }

    public void setIsInit(String isInit) {
        this.isInit = isInit;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    public String getTableNames() {
        return tableNames;
    }

    public void setTableNames(String tableNames) {
        this.tableNames = tableNames;
    }

    public String getProjectPath() {
        return projectPath;
    }

    public void setProjectPath(String projectPath) {
        this.projectPath = projectPath;
    }

    public String getSchemaName() {
        return schemaName;
    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

}