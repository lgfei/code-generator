package com.lgfei.tools.code.generator.core.manager;

import java.util.List;

import com.lgfei.tools.code.generator.model.DatabaseInfo;
import com.lgfei.tools.code.generator.model.MysqlTableInfo;

public interface ICommonManager
{
    String DEFAULT_DBS = "information_schema,mysql,performance_schema,sys";
    
    List<MysqlTableInfo> selectMysqlTables(MysqlTableInfo vo);
    
    List<DatabaseInfo> showDatabases();
}
