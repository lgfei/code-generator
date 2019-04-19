package com.lgfei.code.generator.core.util;

import java.io.File;

/**
 * 文件处理工具类
 * <功能详细描述>
 * 
 * @author  lgfei
 * @version  [版本号, 2019年3月31日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public final class FileUtil
{
    public static void delFolder(String folderPath)
    {
        File file = new File(folderPath);
        if (!file.exists())
        {
            System.out.println(folderPath + "不存在");
            return;
        }
        if (!file.isDirectory())
        {
            System.out.println(folderPath + "不是文件夹");
            return;
        }
        
        File subFile = null;
        String[] fileList = file.list();
        for (String fileName : fileList)
        {
            String filePath = folderPath + File.separator + fileName;
            subFile = new File(filePath);
            if (subFile.isDirectory())
            {
                delFolder(filePath);
            }
            subFile.delete();
            System.out.println(filePath + "已删除");
        }
    }
    
    public static void delFolderFiles(String folderPath, String tableName)
    {
        File file = new File(folderPath);
        if (!file.exists())
        {
            System.out.println(folderPath + "不存在");
            return;
        }
        if (!file.isDirectory())
        {
            System.out.println(folderPath + "不是文件夹");
            return;
        }
        
        File subFile = null;
        String[] fileList = file.list();
        for (String fileName : fileList)
        {
            String filePath = folderPath + File.separator + fileName;
            subFile = new File(filePath);
            if (subFile.isDirectory())
            {
                delFolderFiles(filePath, tableName);
            }
            else if (subFile.isFile() && fileName.contains(tableName))
            {
                subFile.delete();
                System.out.println(filePath + "已删除");
            }
            else
            {
                System.out.println(filePath + "不需要删除");
            }
        }
    }
}
