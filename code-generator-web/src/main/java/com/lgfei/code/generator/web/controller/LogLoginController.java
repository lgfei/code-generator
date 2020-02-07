package com.lgfei.code.generator.web.controller;
 
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lgfei.betterme.framework.api.controller.BaseController;
import com.lgfei.code.generator.common.entity.LogLogin;
import com.lgfei.code.generator.core.service.ILogLoginService;

import io.swagger.annotations.Api;
 
/**
 * <p>
 * 登录日志表 前端控制器
 * </p>
 *
 * @author lgfei
 * @since 2019-07-31
 */
@Api(tags = {"登录日志表"})
@Controller
@RequestMapping("/log-login")
public class  LogLoginController extends BaseController<ILogLoginService, LogLogin, Long> {
    
    @Override
    protected LogLogin newEntity() {
        return new LogLogin();
	}
    
    @RequestMapping(value = "/index.htm", method = RequestMethod.GET)
    public String gotoIndexView() {
        return "log-login/index";
    }
}