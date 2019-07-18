package com.lgfei.code.generator.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lgfei.code.generator.core.ApiCodeGenerator;
import com.lgfei.code.generator.core.service.ICommonService;
import com.lgfei.code.generator.core.service.IDatasourceService;
import com.lgfei.code.generator.model.dto.DatabaseDTO;
import com.lgfei.code.generator.model.dto.MysqlTableDTO;
import com.lgfei.code.generator.model.entity.Datasource;
import com.lgfei.code.generator.model.vo.ApiGeneratorParamVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = { "通用接口" })
@Controller
@RequestMapping("common")
public class CommonController {
    @Autowired
    private ICommonService service;

    @Autowired
    private ApiCodeGenerator apiCodeGenerator;

    @Autowired
    private IDatasourceService datasourceService;

    @ApiOperation("查询表（数据源来源于后台配置）")
    @ResponseBody
    @RequestMapping(value = "/selectMysqlTables", method = { RequestMethod.POST, RequestMethod.GET })
    public List<MysqlTableDTO> selectMysqlTables(String tableSchema, String tableName) {
        MysqlTableDTO vo = new MysqlTableDTO();
        vo.setTableSchema(tableSchema);
        vo.setTableName(tableName);
        return service.selectMysqlTables(vo);
    }

    @ApiOperation("查询库（数据源来源于后台配置）")
    @ResponseBody
    @RequestMapping(value = "/showDatabases", method = { RequestMethod.POST, RequestMethod.GET })
    public List<DatabaseDTO> showDatabases() {
        return service.showDatabases();
    }

    @ApiOperation("查询库")
    @ResponseBody
    @RequestMapping(value = "/getDatabase", method = { RequestMethod.POST, RequestMethod.GET })
    public List<DatabaseDTO> getDatabase(@RequestParam(value = "dsNo") String dsNo) {
        Datasource entity = new Datasource();
        entity.setDsNo(dsNo);
        Wrapper<Datasource> queryWrapper = new QueryWrapper<>(entity);
        Datasource ds = datasourceService.getOne(queryWrapper);
        return service.getDatabase(ds);
    }

    @ApiOperation("查询表")
    @ResponseBody
    @RequestMapping(value = "/getMysqlTables", method = { RequestMethod.POST, RequestMethod.GET })
    public List<MysqlTableDTO> getMysqlTables(@RequestParam(value = "dsNo") String dsNo,
            @RequestParam(value = "schemaName") String schemaName,
            @RequestParam(value = "tableNames", required = false) String tableNames) {
        Datasource entity = new Datasource();
        entity.setDsNo(dsNo);
        Wrapper<Datasource> queryWrapper = new QueryWrapper<>(entity);
        Datasource ds = datasourceService.getOne(queryWrapper);
        return service.getMysqlTables(ds, schemaName, tableNames);
    }

    @ApiOperation("生成Api代码")
    @ResponseBody
    @RequestMapping(value = "/generateApiCode", method = { RequestMethod.POST, RequestMethod.GET })
    public Map<String, Object> generateApiCode(ApiGeneratorParamVO paramVo) {
        Map<String, Object> rs = new HashMap<>();
        try {
            apiCodeGenerator.generate(paramVo);
            rs.put("rsCode", 0);
            rs.put("rsMsg", "Success");
        } catch (Exception e) {
            rs.put("rsCode", "-1");
            rs.put("rsMsg", e.getMessage());
        }
        return rs;
    }
}
