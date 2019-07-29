package com.lgfei.code.generator.web.controller;
 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

import com.lgfei.code.generator.common.entity.SysUser;
import com.lgfei.code.generator.core.service.ISysUserService;

import com.lgfei.betterme.framework.api.controller.BaseController;
 
/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author lgfei
 * @since 2019-07-29
 */
@Api(tags = {"用户信息表"})
@RestController
@RequestMapping("/sys-user")
public class  SysUserController extends BaseController<ISysUserService,SysUser, Long> {
    
    @Override
    protected SysUser newEntity() {
        return new SysUser();
	}
}