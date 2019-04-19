package com.lgfei.code.generator.core.util;

public final class StringUtil
{
    /**
     * 首字母转小写
     * <功能详细描述>
     * @param str
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String toLowerCaseFirstOne(String str)
    {
        if (Character.isLowerCase(str.charAt(0)))
        {
            return str;
        }
        return (new StringBuilder()).append(Character.toLowerCase(str.charAt(0))).append(str.substring(1)).toString();
    }
    
    /**
     * 首字母转大写
     * <功能详细描述>
     * @param str
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String toUpperCaseFirstOne(String str)
    {
        if (Character.isUpperCase(str.charAt(0)))
        {
            return str;
        }
        return (new StringBuilder()).append(Character.toUpperCase(str.charAt(0))).append(str.substring(1)).toString();
    }
}
