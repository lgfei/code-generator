package com.lgfei.code.generator.model.vo;

/**
 * 
 * 页面参数对象
 * <功能详细描述>
 * 
 * @author  lgfei
 * @version  [版本号, 2019年7月3日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class TableParamVO
{
    private Integer isInit;
    
    private String groupId;
    
    private String artifactId;
	    
    private String tableNames;
    
    private String projectPath;
    
    private String tableSchema;

	public Integer getIsInit() {
		return isInit;
	}

	public void setIsInit(Integer isInit) {
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

	public String getTableSchema() {
		return tableSchema;
	}

	public void setTableSchema(String tableSchema) {
		this.tableSchema = tableSchema;
	}
    
}
