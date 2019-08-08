package com.lgfei.code.generator.web.controller;
 
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lgfei.betterme.framework.api.controller.BaseController;
import com.lgfei.code.generator.common.entity.Operation;
import com.lgfei.code.generator.core.security.Authentication;
import com.lgfei.code.generator.core.service.IOperationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
 
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
    
    @ApiOperation("模块分配用户按钮查询接口")
    @ResponseBody
    @RequestMapping(value = "/findModuleOperations.json", method = { RequestMethod.POST, RequestMethod.GET })
    public List<Operation> findModuleOperations(@RequestParam(value = "moduleNo") String moduleNo){
        return service.findUserModuleOpertions(Authentication.SUPPER_ADMIN_USER_NO, moduleNo);
    }
}