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
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lgfei.betterme.framework.api.controller.BaseController;
import com.lgfei.code.generator.common.entity.Datasource;
import com.lgfei.code.generator.common.entity.SysUser;
import com.lgfei.code.generator.common.entity.UserDatasource;
import com.lgfei.code.generator.core.security.Authentication;
import com.lgfei.code.generator.core.service.IDatasourceService;
import com.lgfei.code.generator.core.service.ISysCodeRuleService;
import com.lgfei.code.generator.core.service.IUserDatasourceService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
 
/**
 * <p>
 * 数据源信息表 前端控制器
 * </p>
 *
 * @author lgfei
 * @since 2019-07-29
 */
@Api(tags = {"数据源信息表"})
@Controller
@RequestMapping("/datasource")
public class  DatasourceController extends BaseController<IDatasourceService, Datasource, Long> {
    
    @Autowired
    private IUserDatasourceService userDatasourceService;
    
    @Autowired
    private ISysCodeRuleService sysCodeRuleService;
    
    @Override
    protected Datasource newEntity() {
        return new Datasource();
	}
    
    @Override
    protected Datasource fillNo(Datasource entity) {
        if(null == entity || !StringUtils.isEmpty(entity.getDatasourceNo())) {
            return entity;
        }
        String nextNo = sysCodeRuleService.getNextNo("datasource", "datasource_no");
        if(!StringUtils.isEmpty(nextNo)) {
            entity.setDatasourceNo(nextNo);
        }
        return entity;
    }
    
    @RequestMapping(value = "/index.htm", method = RequestMethod.GET)
    public String gotoIndexView() {
        return "datasource/index";
    }
    
    @RequestMapping(value = "/add.htm", method = RequestMethod.GET)
    public String gotoAddView() {
        return "datasource/add";
    }
    
    @ApiOperation("用户分配数据源查询接口")
    @ResponseBody
    @RequestMapping(value = "/findDatasourceByUser.json", method = { RequestMethod.POST, RequestMethod.GET })
    public Map<String, List<Datasource>> findDatasourceByUser(@RequestParam(value = "userNo") String userNo){
        Map<String,  List<Datasource>> respData = new HashMap<>(2);
        
        List<Datasource> itemsList = new LinkedList<>();
        if(Authentication.isSuperAdmin()) {
            itemsList = getService().list();
        }else {
            SysUser currLoginUser = Authentication.getCurrLoginUser();
            itemsList = findDatasourceByUserNo(currLoginUser.getUserNo());
        }
        List<Datasource> selectedList = findDatasourceByUserNo(userNo);
        
        respData.put("selected", selectedList);
        respData.put("items", itemsList);
        
        return respData;
    }

    private List<Datasource> findDatasourceByUserNo(String userNo) {
        UserDatasource entity = new UserDatasource();
        entity.setUserNo(userNo);
        QueryWrapper<UserDatasource> qwUserDatasource = new QueryWrapper<>(entity);
        List<UserDatasource> list = userDatasourceService.list(qwUserDatasource);
        
        List<Datasource> datasourceList = new LinkedList<>();
        if(!CollectionUtils.isEmpty(list)) {
            Set<String> datasourceNos = new HashSet<>();
            list.forEach(item -> {
                datasourceNos.add(item.getDatasourceNo());
            });
            QueryWrapper<Datasource> qwDatasource = new QueryWrapper<>();
            qwDatasource.in("datasource_no", datasourceNos);
            
            datasourceList = getService().list(qwDatasource);
        }
        
        return datasourceList;
    }

}