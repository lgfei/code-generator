package com.lgfei.code.generator.web.controller;
 
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lgfei.betterme.framework.api.controller.BaseController;
import com.lgfei.code.generator.common.entity.Module;
import com.lgfei.code.generator.common.entity.SysUser;
import com.lgfei.code.generator.common.entity.UserModuleOperation;
import com.lgfei.code.generator.core.security.Authentication;
import com.lgfei.code.generator.core.service.IModuleService;
import com.lgfei.code.generator.core.service.IUserModuleOperationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
 
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
    
    @Autowired
    private IUserModuleOperationService userModuleOperationService;
    
    @Override
    protected Module newEntity() {
        return new Module();
	}
    
    @RequestMapping(value = "/index.htm", method = RequestMethod.GET)
    public String gotoIndexView() {
        return "module/index";
    }
    
    @ApiOperation("用户分配数据源查询接口")
    @ResponseBody
    @RequestMapping(value = "/findModuleByUser.json", method = { RequestMethod.POST, RequestMethod.GET })
    public Map<String, List<Module>> findModuleByUser(@RequestParam(value = "userNo") String userNo){
        Map<String,  List<Module>> respData = new HashMap<>(2);
        
        List<Module> itemsList = new LinkedList<>();
        if(Authentication.isSuperAdmin()) {
            itemsList = getService().list();
        }else {
            SysUser currLoginUser = Authentication.getCurrLoginUser();
            itemsList = findModuleByUserNo(currLoginUser.getUserNo());
        }
        List<Module> selectedList = findModuleByUserNo(userNo);
        
        respData.put("selected", selectedList);
        respData.put("items", itemsList);
        
        return respData;
    }
    
    private List<Module> findModuleByUserNo(String userNo) {
        UserModuleOperation entity = new UserModuleOperation();
        entity.setUserNo(userNo);
        QueryWrapper<UserModuleOperation> qwUserDatasource = new QueryWrapper<>(entity);
        List<UserModuleOperation> list = userModuleOperationService.list(qwUserDatasource);
        
        List<Module> moduleList = new LinkedList<>();
        if(!CollectionUtils.isEmpty(list)) {
            Set<String> moduleNos = new HashSet<>();
            list.forEach(item -> {
                moduleNos.add(item.getModuleNo());
            });
            QueryWrapper<Module> qwDatasource = new QueryWrapper<>();
            qwDatasource.in("module_no", moduleNos);
            
            moduleList = getService().list(qwDatasource);
        }
        
        return moduleList;
    }
}