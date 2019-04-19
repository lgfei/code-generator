package com.lgfei.code.generator.core.manager.impl;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lgfei.code.generator.core.manager.ICommonManager;
import com.lgfei.code.generator.core.service.ICommonService;
import com.lgfei.code.generator.model.DatabaseInfo;
import com.lgfei.code.generator.model.MysqlTableInfo;

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
}
