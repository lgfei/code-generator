package com.lgfei.code.generator.common.entity;

public class UserDsRelation extends BaseEntity
{
    
    private static final long serialVersionUID = 1L;
    
    private String userNo;
    
    private String dsNo;
    
    private Integer allowNum;
    
    public String getUserNo()
    {
        return userNo;
    }
    
    public void setUserNo(String userNo)
    {
        this.userNo = userNo;
    }
    
    public String getDsNo()
    {
        return dsNo;
    }
    
    public void setDsNo(String dsNo)
    {
        this.dsNo = dsNo;
    }
    
    public Integer getAllowNum()
    {
        return allowNum;
    }
    
    public void setAllowNum(Integer allowNum)
    {
        this.allowNum = allowNum;
    }
}
