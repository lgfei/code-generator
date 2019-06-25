package com.lgfei.code.generator.core.manager;

import java.util.List;

import com.lgfei.code.generator.model.DatabaseInfo;
import com.lgfei.code.generator.model.MysqlTableInfo;
import com.lgfei.code.generator.model.ParamVO;

public interface ICommonManager
{
    String DEFAULT_DBS = "information_schema,mysql,performance_schema,sys";
    
    List<MysqlTableInfo> selectMysqlTables(MysqlTableInfo vo);
    
    List<MysqlTableInfo> getMysqlTables(MysqlTableInfo vo);
    
    List<DatabaseInfo> showDatabases();
    
    List<DatabaseInfo> getDatabase(ParamVO paramVO);
}
