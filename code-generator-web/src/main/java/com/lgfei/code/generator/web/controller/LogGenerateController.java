package com.lgfei.code.generator.web.controller;
 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

import com.lgfei.code.generator.common.entity.LogGenerate;
import com.lgfei.code.generator.core.service.ILogGenerateService;

import com.lgfei.betterme.framework.api.controller.BaseController;
 
/**
 * <p>
 * 生成日志表 前端控制器
 * </p>
 *
 * @author lgfei
 * @since 2019-07-29
 */
@Api(tags = {"生成日志表"})
@RestController
@RequestMapping("/log-generate")
public class  LogGenerateController extends BaseController<ILogGenerateService,LogGenerate, Long> {
    
    @Override
    protected LogGenerate newEntity() {
        return new LogGenerate();
	}
}