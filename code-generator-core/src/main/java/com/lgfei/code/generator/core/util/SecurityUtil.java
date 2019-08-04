package com.lgfei.code.generator.core.util;

import com.lgfei.code.generator.common.entity.SysUser;

public final class SecurityUtil {
    
    public static SysUser getCurrLoginUser() {
        SysUser currLoginUser = new SysUser();
        currLoginUser.setUserNo("U00001");
        currLoginUser.setAccount("admin");
        return currLoginUser;
    }
    
    public static boolean isSuperAdmin() {
        SysUser currLoginUser = getCurrLoginUser();
        if("U00001".equals(currLoginUser.getUserNo())) {
            return true;
        }
        return false;
    }
}
