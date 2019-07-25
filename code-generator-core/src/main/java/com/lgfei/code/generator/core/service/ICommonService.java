package com.lgfei.code.generator.core.service;

import java.util.List;

import com.lgfei.code.generator.common.dto.DatabaseDTO;
import com.lgfei.code.generator.common.dto.MysqlTableDTO;
import com.lgfei.code.generator.common.entity.Datasource;

public interface ICommonService {
    String DEFAULT_SCHEMAS = "information_schema,mysql,performance_schema,sys";

    List<MysqlTableDTO> selectMysqlTables(MysqlTableDTO vo);

    List<DatabaseDTO> showDatabases();

    List<MysqlTableDTO> getMysqlTables(Datasource ds, String schemaName, String tableNames);

    List<DatabaseDTO> getDatabase(Datasource ds);
}
