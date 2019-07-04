package com.lgfei.code.generator.core.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.lgfei.code.generator.model.vo.ParamVO;

/**
 * JDBC处理工具类
 * <功能详细描述>
 * 
 * @author  lgfei
 * @version  [版本号, 2019年5月12日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public final class JdbcUtil
{
    private static final String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
    
    public static Connection getConn(ParamVO paramVO)
    {
        String url = new StringBuffer("jdbc:mysql://").append(paramVO.getDbServer())
            .append(':')
            .append(paramVO.getDbPort())
            .append('/')
            .append(paramVO.getDbName())
            .toString();
        Connection conn = null;
        try
        {
            Class.forName(MYSQL_DRIVER);
            conn = (Connection)DriverManager.getConnection(url, paramVO.getDbUserName(), paramVO.getDbPassword());
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return conn;
    }
}
