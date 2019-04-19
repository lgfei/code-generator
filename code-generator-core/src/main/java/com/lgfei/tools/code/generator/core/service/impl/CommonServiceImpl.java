package com.lgfei.tools.code.generator.core.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lgfei.tools.code.generator.core.mapper.CommonMapper;
import com.lgfei.tools.code.generator.core.service.ICommonService;
import com.lgfei.tools.code.generator.model.MysqlTableInfo;

@Service
public class CommonServiceImpl implements ICommonService
{
    @Autowired
    private CommonMapper mapper;
    
    @Override
    public List<MysqlTableInfo> selectMysqlTables(MysqlTableInfo vo)
    {
        Map<String, Object> params = new HashMap<>();
        params.put("tableSchema", vo.getTableSchema());
        params.put("tableName", vo.getTableName());
        return mapper.selectMysqlTables(params);
    }
    
    @Override
    public List<String> showDatabases()
    {
        return mapper.showDatabases();
    }
    
}
