package com.lgfei.code.generator.core.service;

import java.util.List;

import com.lgfei.code.generator.model.dto.DatabaseDTO;
import com.lgfei.code.generator.model.dto.MysqlTableDTO;
import com.lgfei.code.generator.model.vo.ParamVO;

public interface ICommonService
{
	String DEFAULT_DBS = "information_schema,mysql,performance_schema,sys";
	
    List<MysqlTableDTO> selectMysqlTables(MysqlTableDTO vo);
    
    List<DatabaseDTO> showDatabases();
    
    List<MysqlTableDTO> getMysqlTables(ParamVO paramVO);
    
    List<DatabaseDTO> getDatabase(ParamVO paramVO);
}
