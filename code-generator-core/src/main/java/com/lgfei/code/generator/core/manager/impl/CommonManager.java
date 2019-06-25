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

import com.lgfei.code.generator.core.manager.ICommonManager;
import com.lgfei.code.generator.core.service.ICommonService;
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
    public List<MysqlTableInfo> getMysqlTables(MysqlTableInfo vo)
    {
        // TODO Auto-generated method stub
        return null;
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
            int col = rs.getMetaData().getColumnCount();
            while (rs.next())
            {
                DatabaseInfo info = new DatabaseInfo();
                for (int i = 1; i <= col; i++)
                {
                    info.setDbName(rs.getString(i));
                    info.setDbDesc(rs.getString(i));
                }
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
