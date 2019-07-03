package com.lgfei.code.generator.core.manager.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.lgfei.code.generator.core.manager.ICommonManager;
import com.lgfei.code.generator.core.service.ICommonService;
import com.lgfei.code.generator.core.util.DBParams;
import com.lgfei.code.generator.core.util.JdbcUtil;
import com.lgfei.code.generator.model.DatabaseInfo;
import com.lgfei.code.generator.model.MysqlTableInfo;
import com.lgfei.code.generator.model.ParamVO;

@Service
public class CommonManager implements ICommonManager
{
    @Autowired
    private ICommonService service;
    
    @Override
    public List<MysqlTableInfo> selectMysqlTables(MysqlTableInfo vo)
    {
        return service.selectMysqlTables(vo);
    }
    
    @Override
    public List<MysqlTableInfo> getMysqlTables(ParamVO paramVO)
    {
        List<MysqlTableInfo> list = null;
        Connection conn = JdbcUtil.getConn(paramVO);
        StringBuilder sql = new StringBuilder(
            "SELECT TABLE_SCHEMA AS tableSchema, TABLE_NAME AS tableName, TABLE_TYPE AS tableType, CASE TABLE_COMMENT WHEN '' THEN TABLE_NAME ELSE TABLE_COMMENT END AS tableComment FROM information_schema.`TABLES` WHERE 1 = 1");
        DBParams params = new DBParams();
        if (StringUtils.isEmpty(paramVO.getTableSchema()))
        {
            sql.append(" AND 1=2");
        }
        else
        {
            sql.append(" AND table_schema = ?");
            params.addParam(paramVO.getTableSchema());
        }
        if (!StringUtils.isEmpty(paramVO.getTableNames()))
        {
            sql.append(" AND (table_name LIKE ? OR table_comment LIKE ?)");
            params.addParam(paramVO.getTableNames());
        }
        PreparedStatement pstmt;
        try
        {
            list = new ArrayList<>();
            pstmt = (PreparedStatement)conn.prepareStatement(sql.toString());
            //为sql语句设置参数  
            params.prepareStatement(pstmt);
            
            ResultSet rs = pstmt.executeQuery();
            //int col = rs.getMetaData().getColumnCount();
            while (rs.next())
            {
                MysqlTableInfo info = new MysqlTableInfo();
                info.setTableSchema(rs.getString(1));
                info.setTableName(rs.getString(2));
                info.setTableType(rs.getString(3));
                info.setTableComment(rs.getString(4));
                list.add(info);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return list;
    }
    
    @Override
    public List<DatabaseInfo> showDatabases()
    {
        List<String> rows = service.showDatabases();
        List<DatabaseInfo> dbs = new LinkedList<>();
        for (String row : rows)
        {
            if (DEFAULT_DBS.contains(row))
            {
                continue;
            }
            DatabaseInfo db = new DatabaseInfo();
            db.setDbName(row);
            db.setDbDesc(row);
            dbs.add(db);
        }
        return dbs;
    }
    
    @Override
    public List<DatabaseInfo> getDatabase(ParamVO paramVO)
    {
        List<DatabaseInfo> list = null;
        Connection conn = JdbcUtil.getConn(paramVO);
        String sql = "show databases";
        PreparedStatement pstmt;
        try
        {
            list = new ArrayList<>();
            pstmt = (PreparedStatement)conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            //int col = rs.getMetaData().getColumnCount();
            while (rs.next())
            {
                DatabaseInfo info = new DatabaseInfo();
                info.setDbName(rs.getString(1));
                info.setDbDesc(rs.getString(1));
                list.add(info);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return list;
    }
    
}
