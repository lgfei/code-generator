package com.lgfei.code.generator.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lgfei.code.generator.core.service.ISysUserService;
import com.lgfei.code.generator.common.entity.SysUser;

import io.swagger.annotations.Api;

@Api(tags = { "用户信息接口" })
@Controller
@RequestMapping("sys-user")
public class SysUserController extends BaseController<SysUser> {

    @Autowired
    private ISysUserService service;

    @Override
    protected IService<SysUser> getService() {
        return service;
    }

}
