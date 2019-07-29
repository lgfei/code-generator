package com.lgfei.code.generator.web.controller;
 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

import com.lgfei.code.generator.common.entity.Module;
import com.lgfei.code.generator.core.service.IModuleService;

import com.lgfei.betterme.framework.api.controller.BaseController;
 
/**
 * <p>
 * 模块信息表 前端控制器
 * </p>
 *
 * @author lgfei
 * @since 2019-07-29
 */
@Api(tags = {"模块信息表"})
@RestController
@RequestMapping("/module")
public class  ModuleController extends BaseController<IModuleService,Module, Long> {
    
    @Override
    protected Module newEntity() {
        return new Module();
	}
}