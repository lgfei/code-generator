package com.lgfei.code.generator.core.security;

import com.lgfei.code.generator.common.entity.SysUser;

public final class Authentication {
    
    public static final String SUPPER_ADMIN_USER_NO = "U00001";
    
    public static final String SUPPER_ADMIN_ACCOUNT = "admin";
    
    public static SysUser getCurrLoginUser() {
        SysUser currLoginUser = new SysUser();
        currLoginUser.setUserNo(SUPPER_ADMIN_USER_NO);
        currLoginUser.setAccount(SUPPER_ADMIN_ACCOUNT);
        return currLoginUser;
    }
    
    public static boolean isSuperAdmin() {
        SysUser currLoginUser = getCurrLoginUser();
        if(SUPPER_ADMIN_USER_NO.equals(currLoginUser.getUserNo())) {
            return true;
        }
        return false;
    }
}
