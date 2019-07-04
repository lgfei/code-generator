package com.lgfei.code.generator.model.dto;

import java.io.Serializable;

public class MysqlTableDTO implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    private String tableSchema;
    
    private String tableName;
    
    private String tableType;
    
    private String tableComment;
    
    public String getTableSchema()
    {
        return tableSchema;
    }
    
    public void setTableSchema(String tableSchema)
    {
        this.tableSchema = tableSchema;
    }
    
    public String getTableName()
    {
        return tableName;
    }
    
    public void setTableName(String tableName)
    {
        this.tableName = tableName;
    }
    
    public String getTableType()
    {
        return tableType;
    }
    
    public void setTableType(String tableType)
    {
        this.tableType = tableType;
    }
    
    public String getTableComment()
    {
        return tableComment;
    }
    
    public void setTableComment(String tableComment)
    {
        this.tableComment = tableComment;
    }
    
    //    @Override
    //    public String toString()
    //    {
    //        return JSONObject.toJSONString(this);
    //    }
    
}
