package com.lgfei.code.generator.core.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.lgfei.code.generator.core.mapper.CommonMapper;
import com.lgfei.code.generator.core.service.ICommonService;
import com.lgfei.code.generator.core.util.DBParams;
import com.lgfei.code.generator.core.util.JdbcUtil;
import com.lgfei.code.generator.model.dto.DatabaseDTO;
import com.lgfei.code.generator.model.dto.MysqlTableDTO;
import com.lgfei.code.generator.model.entity.Datasource;

@Service
public class CommonServiceImpl implements ICommonService {
    @Autowired
    private CommonMapper mapper;

    @Override
    public List<MysqlTableDTO> selectMysqlTables(MysqlTableDTO vo) {
        Map<String, Object> params = new HashMap<>();
        params.put("tableSchema", vo.getTableSchema());
        params.put("tableName", vo.getTableName());
        return mapper.selectMysqlTables(params);
    }

    @Override
    public List<DatabaseDTO> showDatabases() {
        List<String> rows = mapper.showDatabases();
        List<DatabaseDTO> dbs = new LinkedList<>();
        for (String row : rows) {
            if (DEFAULT_SCHEMAS.contains(row)) {
                continue;
            }
            DatabaseDTO db = new DatabaseDTO();
            db.setDbName(row);
            db.setDbDesc(row);
            dbs.add(db);
        }
        return dbs;
    }

    @Override
    public List<MysqlTableDTO> getMysqlTables(Datasource ds, String schemaName, String tableNames) {
        List<MysqlTableDTO> list = null;
        Connection conn = JdbcUtil.getConn(ds);
        StringBuilder sql = new StringBuilder(
                "SELECT TABLE_SCHEMA AS tableSchema, TABLE_NAME AS tableName, TABLE_TYPE AS tableType, CASE TABLE_COMMENT WHEN '' THEN TABLE_NAME ELSE TABLE_COMMENT END AS tableComment FROM information_schema.`TABLES` WHERE 1 = 1");
        DBParams params = new DBParams();
        if (StringUtils.isEmpty(schemaName)) {
            sql.append(" AND 1=2");
        } else {
            sql.append(" AND table_schema = ?");
            params.addParam(schemaName);
        }
        if (!StringUtils.isEmpty(tableNames)) {
            sql.append(" AND (table_name LIKE ? OR table_comment LIKE ?)");
            params.addParam(tableNames);
        }
        PreparedStatement pstmt;
        try {
            list = new ArrayList<>();
            pstmt = (PreparedStatement) conn.prepareStatement(sql.toString());
            // 为sql语句设置参数
            params.prepareStatement(pstmt);

            ResultSet rs = pstmt.executeQuery();
            // int col = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                MysqlTableDTO info = new MysqlTableDTO();
                info.setTableSchema(rs.getString(1));
                info.setTableName(rs.getString(2));
                info.setTableType(rs.getString(3));
                info.setTableComment(rs.getString(4));
                list.add(info);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<DatabaseDTO> getDatabase(Datasource ds) {
        List<DatabaseDTO> list = null;
        Connection conn = JdbcUtil.getConn(ds);
        String sql = "show databases";
        PreparedStatement pstmt;
        try {
            list = new ArrayList<>();
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            // int col = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                String schemaName = rs.getString(1);
                if (!DEFAULT_SCHEMAS.contains(schemaName)) {
                    DatabaseDTO info = new DatabaseDTO();
                    info.setDbName(rs.getString(1));
                    info.setDbDesc(rs.getString(1));
                    list.add(info);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}
