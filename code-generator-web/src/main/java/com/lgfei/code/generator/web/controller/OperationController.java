package com.lgfei.code.generator.web.controller;
 
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lgfei.betterme.framework.api.controller.BaseController;
import com.lgfei.code.generator.common.entity.Operation;
import com.lgfei.code.generator.core.service.IOperationService;

import io.swagger.annotations.Api;
 
/**
 * <p>
 * 按钮信息表 前端控制器
 * </p>
 *
 * @author lgfei
 * @since 2019-07-31
 */
@Api(tags = {"按钮信息表"})
@Controller
@RequestMapping("/operation")
public class  OperationController extends BaseController<IOperationService, Operation, Long> {
    
    @Override
    protected Operation newEntity() {
        return new Operation();
	}
    
    @RequestMapping(value = "/index.htm", method = RequestMethod.GET)
    public String gotoIndexView() {
        return "operation/index";
    }
    
    
}