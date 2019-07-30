package com.lgfei.code.generator.web.controller;
 
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lgfei.betterme.framework.api.controller.BaseController;
import com.lgfei.code.generator.common.entity.Datasource;
import com.lgfei.code.generator.core.service.IDatasourceService;

import io.swagger.annotations.Api;
 
/**
 * <p>
 * 数据源信息表 前端控制器
 * </p>
 *
 * @author lgfei
 * @since 2019-07-29
 */
@Api(tags = {"数据源信息表"})
@Controller
@RequestMapping("/datasource")
public class  DatasourceController extends BaseController<IDatasourceService, Datasource, Long> {
    
    @Override
    protected Datasource newEntity() {
        return new Datasource();
	}
    
    @RequestMapping(value = "/index.htm", method = RequestMethod.GET)
    public String gotoIndexView() {
        return "datasource/index";
    }
}