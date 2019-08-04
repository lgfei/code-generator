package com.lgfei.code.generator.web.controller;
 
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lgfei.betterme.framework.api.controller.BaseController;
import com.lgfei.betterme.framework.common.vo.ListResponseVO;
import com.lgfei.code.generator.common.entity.UserModuleOperation;
import com.lgfei.code.generator.common.vo.LayuiTreeVO;
import com.lgfei.code.generator.core.service.IUserModuleOperationService;
import com.lgfei.code.generator.core.util.SecurityUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
 
/**
 * <p>
 * 用户模块关系表 前端控制器
 * </p>
 *
 * @author lgfei
 * @since 2019-07-31
 */
@Api(tags = {"用户模块关系表"})
@Controller
@RequestMapping("/user-module-operation")
public class  UserModuleOperationController extends BaseController<IUserModuleOperationService, UserModuleOperation, Long> {
    
    @Override
    protected UserModuleOperation newEntity() {
        return new UserModuleOperation();
	}
    
    @ApiOperation("查询用户模块权限信息")
    @ResponseBody
    @RequestMapping(value = "/findUserModuleOperations.json", method = { RequestMethod.POST, RequestMethod.GET })
    public List<LayuiTreeVO> findUserModuleOperations(@RequestParam("userNo") String userNo) {
        UserModuleOperation entity = new UserModuleOperation();
        entity.setUserNo(SecurityUtil.SUPPER_ADMIN_USER_NO);
        
        QueryWrapper<UserModuleOperation> qw = new QueryWrapper<>(entity);
        List<UserModuleOperation> list = getService().list(qw);
        
        List<LayuiTreeVO> respData = new LinkedList<>();
        list.forEach(item -> {
            LayuiTreeVO vo = new LayuiTreeVO();
            vo.setId(item.getModuleNo());
            vo.setTitle(item.getModuleNo());
            vo.setSpread(true);
            String operationNos = item.getOperations();
            String[] arr = operationNos.split(",");
            List<LayuiTreeVO> children = new LinkedList<>();
            for (String operation : arr) {
                LayuiTreeVO subVo = new LayuiTreeVO();
                subVo.setId(operation);
                subVo.setTitle(operation);
                children.add(subVo);
            }
            vo.setChildren(children);
            
            respData.add(vo);
        });
        
        return respData;
    }
    
    @ApiOperation("保存用户模块权限信息")
    @ResponseBody
    @RequestMapping(value = "/saveUserModuleOperations.json", method = { RequestMethod.POST, RequestMethod.GET })
    public ListResponseVO<UserModuleOperation> saveUserModuleOperations(@RequestParam("rows") String rows) {
        if(StringUtils.isEmpty(rows)) {
            return new ListResponseVO.Builder<UserModuleOperation>().illegal();
        }
        
        List<UserModuleOperation> list = JSONArray.parseArray(rows, UserModuleOperation.class);
        String userNo = list.get(0).getUserNo();
        
        UserModuleOperation entity = new UserModuleOperation();
        entity.setUserNo(userNo);
        
        QueryWrapper<UserModuleOperation> qw = new QueryWrapper<>(entity);
        // 先删除
        boolean removeFlag = getService().remove(qw);
        if(removeFlag) {
            // 重新插入
            getService().saveBatch(list);
        }
        return new ListResponseVO.Builder<UserModuleOperation>().ok();
    }
}