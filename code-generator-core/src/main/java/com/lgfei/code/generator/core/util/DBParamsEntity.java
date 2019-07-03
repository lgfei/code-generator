package com.lgfei.code.generator.core.util;

public class DBParamsEntity
{
    /** 该jdbc数据类型的哪个类型  */
    private Object value;//
    
    /** jdbc数据类型的哪一种 */
    private int sqlType;
    
    /***  
     *   
     * @param value  
     * @param sqlType  
     */
    public DBParamsEntity(Object value, int sqlType)
    {
        this.value = value;
        this.sqlType = sqlType;
    }
    
    public Object getValue()
    {
        return value;
    }
    
    public void setValue(Object value)
    {
        this.value = value;
    }
    
    public int getValueType()
    {
        return sqlType;
    }
    
    public void setValueType(int sqlType)
    {
        this.sqlType = sqlType;
        
    }
}
