package com.lgfei.code.generator.model;

import java.io.Serializable;

public class DatabaseInfo implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    String dbName;
    
    String dbDesc;
    
    public String getDbName()
    {
        return dbName;
    }
    
    public void setDbName(String dbName)
    {
        this.dbName = dbName;
    }
    
    public String getDbDesc()
    {
        return dbDesc;
    }
    
    public void setDbDesc(String dbDesc)
    {
        this.dbDesc = dbDesc;
    }
    
}
