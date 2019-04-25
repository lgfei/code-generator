package com.lgfei.code.generator.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lgfei.code.generator.core.ApiCodeGenerator;
import com.lgfei.code.generator.core.manager.ICommonManager;
import com.lgfei.code.generator.model.DatabaseInfo;
import com.lgfei.code.generator.model.MysqlTableInfo;
import com.lgfei.code.generator.model.ParamVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = {"通用接口"})
@Controller
@RequestMapping("common")
public class CommonController
{
    @Autowired
    private ICommonManager manager;
    
    @Autowired
    private ApiCodeGenerator apiCodeGenerator;
    
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
    public List<DatabaseInfo> showDatabases()
    {
        return manager.showDatabases();
    }
    
    @ApiOperation("生成Api代码")
    @ResponseBody
    @RequestMapping(value = "/generateApiCode", method = {RequestMethod.POST, RequestMethod.GET})
    public Map<String, Object> generateApiCode(ParamVO paramVO)
    {
        Map<String, Object> rs = new HashMap<>();
        try
        {
            apiCodeGenerator.generate(paramVO);
            rs.put("rsCode", 0);
            rs.put("rsMsg", "Success");
        }
        catch (Exception e)
        {
            rs.put("rsCode", "-1");
            rs.put("rsMsg", e.getMessage());
        }
        return rs;
    }
}
