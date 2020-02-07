package com.lgfei.code.generator.web.controller;
 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

import com.lgfei.code.generator.common.entity.SysCodeRule;
import com.lgfei.code.generator.core.service.ISysCodeRuleService;

import com.lgfei.betterme.framework.api.controller.BaseController;
 
/**
 * <p>
 * 编码规则表 前端控制器
 * </p>
 *
 * @author lgfei
 * @since 2019-08-25
 */
@Api(tags = {"编码规则表"})
@RestController
@RequestMapping("/sys-code-rule")
public class  SysCodeRuleController extends BaseController<ISysCodeRuleService, SysCodeRule, Long> {
    
    @Override
    protected SysCodeRule newEntity() {
        return new SysCodeRule();
	}
}