package com.lgfei.code.generator.web.controller;
 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

import com.lgfei.code.generator.common.entity.UserModuleOperation;
import com.lgfei.code.generator.core.service.IUserModuleOperationService;

import com.lgfei.betterme.framework.api.controller.BaseController;
 
/**
 * <p>
 * 用户模块关系表 前端控制器
 * </p>
 *
 * @author lgfei
 * @since 2019-07-31
 */
@Api(tags = {"用户模块关系表"})
@RestController
@RequestMapping("/user-module-operation")
public class  UserModuleOperationController extends BaseController<IUserModuleOperationService, UserModuleOperation, Long> {
    
    @Override
    protected UserModuleOperation newEntity() {
        return new UserModuleOperation();
	}
}