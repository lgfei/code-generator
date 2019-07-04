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
public class ParamVO
{
    private Integer isInit;
    
    private String groupId;
    
    private String artifactId;
    
    private String dbServer;
    
    private String dbPort;
    
    private String dbUserName;
    
    private String dbPassword;
    
    private String dbName;
    
    private String tableNames;
    
    private String projectPath;
    
    private String tableSchema;
    
    public Integer getIsInit()
    {
        return isInit;
    }
    
    public void setIsInit(Integer isInit)
    {
        this.isInit = isInit;
    }
    
    public String getGroupId()
    {
        return groupId;
    }
    
    public void setGroupId(String groupId)
    {
        this.groupId = groupId;
    }
    
    public String getArtifactId()
    {
        return artifactId;
    }
    
    public void setArtifactId(String artifactId)
    {
        this.artifactId = artifactId;
    }
    
    public String getDbServer()
    {
        return dbServer;
    }
    
    public void setDbServer(String dbServer)
    {
        this.dbServer = dbServer;
    }
    
    public String getDbPort()
    {
        return dbPort;
    }
    
    public void setDbPort(String dbPort)
    {
        this.dbPort = dbPort;
    }
    
    public String getDbUserName()
    {
        return dbUserName;
    }
    
    public void setDbUserName(String dbUserName)
    {
        this.dbUserName = dbUserName;
    }
    
    public String getDbPassword()
    {
        return dbPassword;
    }
    
    public void setDbPassword(String dbPassword)
    {
        this.dbPassword = dbPassword;
    }
    
    public String getDbName()
    {
        return dbName;
    }
    
    public void setDbName(String dbName)
    {
        this.dbName = dbName;
    }
    
    public String getTableNames()
    {
        return tableNames;
    }
    
    public void setTableNames(String tableNames)
    {
        this.tableNames = tableNames;
    }
    
    public String getProjectPath()
    {
        return projectPath;
    }
    
    public void setProjectPath(String projectPath)
    {
        this.projectPath = projectPath;
    }
    
    public String getTableSchema()
    {
        return tableSchema;
    }
    
    public void setTableSchema(String tableSchema)
    {
        this.tableSchema = tableSchema;
    }
    
}
