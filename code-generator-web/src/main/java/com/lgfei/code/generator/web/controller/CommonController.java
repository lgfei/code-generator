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
import com.lgfei.code.generator.common.dto.DatabaseDTO;
import com.lgfei.code.generator.common.dto.MysqlTableDTO;
import com.lgfei.code.generator.common.entity.Datasource;
import com.lgfei.code.generator.common.vo.ApiGeneratorParamVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = { "通用接口" })
@Controller
@RequestMapping("")
public class CommonController {
    @Autowired
    private ICommonService service;

    @Autowired
    private ApiCodeGenerator apiCodeGenerator;

    @Autowired
    private IDatasourceService datasourceService;

    @RequestMapping(value = "/index.htm", method = RequestMethod.GET)
    public String gotoIndexView() {
        return "index";
    }
    
    @RequestMapping(value = "/common.htm", method = RequestMethod.GET)
    public String gotoCommonView() {
        return "common";
    }

    @ApiOperation("查询库")
    @ResponseBody
    @RequestMapping(value = "/getDatabase.json", method = { RequestMethod.POST, RequestMethod.GET })
    public List<DatabaseDTO> getDatabase(@RequestParam(value = "datasourceNo") String datasourceNo) {
        Datasource entity = new Datasource();
        entity.setDatasourceNo(datasourceNo);
        Wrapper<Datasource> queryWrapper = new QueryWrapper<>(entity);
        Datasource ds = datasourceService.getOne(queryWrapper);
        return service.getDatabase(ds);
    }

    @ApiOperation("查询表")
    @ResponseBody
    @RequestMapping(value = "/getMysqlTables.json", method = { RequestMethod.POST, RequestMethod.GET })
    public List<MysqlTableDTO> getMysqlTables(@RequestParam(value = "datasourceNo") String datasourceNo,
            @RequestParam(value = "schemaName") String schemaName,
            @RequestParam(value = "tableNames", required = false) String tableNames) {
        Datasource entity = new Datasource();
        entity.setDatasourceNo(datasourceNo);
        Wrapper<Datasource> queryWrapper = new QueryWrapper<>(entity);
        Datasource ds = datasourceService.getOne(queryWrapper);
        return service.getMysqlTables(ds, schemaName, tableNames);
    }

    @ApiOperation("生成Api代码")
    @ResponseBody
    @RequestMapping(value = "/generateApiCode.json", method = { RequestMethod.POST, RequestMethod.GET })
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
