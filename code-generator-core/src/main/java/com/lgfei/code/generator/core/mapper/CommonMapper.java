package com.lgfei.code.generator.core.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.lgfei.code.generator.model.dto.MysqlTableDTO;

@Mapper
public interface CommonMapper
{
    //@Select("SELECT TABLE_SCHEMA AS tableSchema, TABLE_NAME AS tableName, TABLE_TYPE AS tableType, TABLE_COMMENT AS tableComment FROM information_schema.`TABLES` WHERE table_schema = 'betterme_admin';")
    List<MysqlTableDTO> selectMysqlTables(@Param("params") Map<String, Object> params);
    
    @Select("show databases")
    List<String> showDatabases();
}
