package com.lgfei.code.generator.core.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.lgfei.code.generator.model.entity.Datasource;

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
    public static Connection getConn(Datasource ds)
    {
        String url = new StringBuffer("jdbc:mysql://").append(ds.getServer())
            .append('/')
            .append(ds.getName())
            .toString();
        Connection conn = null;
        try
        {
            Class.forName(ds.getDriver());
            conn = (Connection)DriverManager.getConnection(url, ds.getUsername(), ds.getPassword());
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
