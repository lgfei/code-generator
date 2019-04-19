package com.lgfei.code.generator.core.service;

import java.util.List;

import com.lgfei.code.generator.model.MysqlTableInfo;

public interface ICommonService
{
    List<MysqlTableInfo> selectMysqlTables(MysqlTableInfo vo);
    
    List<String> showDatabases();
}
