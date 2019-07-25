package com.lgfei.code.generator.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lgfei.code.generator.core.service.IDatasourceService;
import com.lgfei.code.generator.common.entity.Datasource;

import io.swagger.annotations.Api;

@Api(tags = { "数据源信息接口" })
@Controller
@RequestMapping("datasource")
public class DatasourceController extends BaseController<Datasource> {

    @Autowired
    private IDatasourceService service;

    @Override
    protected IService<Datasource> getService() {
        return service;
    }

}
