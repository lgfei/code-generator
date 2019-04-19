package com.lgfei.code.generator.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lgfei.code.generator.core.manager.ICommonManager;
import com.lgfei.code.generator.model.DatabaseInfo;
import com.lgfei.code.generator.model.MysqlTableInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = {"通用接口"})
@Controller
@RequestMapping("common")
public class CommonController
{
    @Autowired
    private ICommonManager manager;
    
    @ApiOperation("查询表")
    @ResponseBody
    @RequestMapping(value = "/selectMysqlTables", method = {RequestMethod.POST, RequestMethod.GET})
    public List<MysqlTableInfo> selectMysqlTables(String tableSchema, String tableName)
    {
        MysqlTableInfo vo = new MysqlTableInfo();
        vo.setTableSchema(tableSchema);
        vo.setTableName(tableName);
        return manager.selectMysqlTables(vo);
    }
    
    @ApiOperation("查询库")
    @ResponseBody
    @RequestMapping(value = "/showDatabases", method = {RequestMethod.POST, RequestMethod.GET})
    List<DatabaseInfo> showDatabases()
    {
        return manager.showDatabases();
    }
}
