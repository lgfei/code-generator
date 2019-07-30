package com.lgfei.code.generator.web.controller;
 
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lgfei.betterme.framework.api.controller.BaseController;
import com.lgfei.code.generator.common.entity.Module;
import com.lgfei.code.generator.core.service.IModuleService;

import io.swagger.annotations.Api;
 
/**
 * <p>
 * 模块信息表 前端控制器
 * </p>
 *
 * @author lgfei
 * @since 2019-07-29
 */
@Api(tags = {"模块信息表"})
@Controller
@RequestMapping("/module")
public class  ModuleController extends BaseController<IModuleService,Module, Long> {
    
    @Override
    protected Module newEntity() {
        return new Module();
	}
    
    @RequestMapping(value = "/index.htm", method = RequestMethod.GET)
    public String gotoIndexView() {
        return "module/index";
    }
}