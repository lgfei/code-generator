package com.lgfei.code.generator.web.controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.google.common.collect.Lists;
import com.lgfei.betterme.framework.api.controller.BaseController;
import com.lgfei.betterme.framework.common.vo.BatchRequestVO;
import com.lgfei.betterme.framework.common.vo.ListResponseVO;
import com.lgfei.betterme.framework.common.vo.RequestVO;
import com.lgfei.code.generator.common.entity.UserModuleOperation;
import com.lgfei.code.generator.common.vo.LayuiTreeVO;
import com.lgfei.code.generator.core.security.Authentication;
import com.lgfei.code.generator.core.service.IUserModuleOperationService;

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
@Api(tags = { "用户模块关系表" })
@Controller
@RequestMapping("/user-module-operation")
public class UserModuleOperationController  extends BaseController<IUserModuleOperationService, UserModuleOperation, Long> {

    @Override
    protected UserModuleOperation newEntity() {
        return new UserModuleOperation();
    }

    @ApiOperation("查询用户模块权限信息")
    @ResponseBody
    @RequestMapping(value = "/findUserModuleOperations.json", method = { RequestMethod.POST, RequestMethod.GET })
    public List<LayuiTreeVO> findUserModuleOperations(@RequestParam("userNo") String userNo) {
        if (StringUtils.isEmpty(userNo)) {
            return null;
        }
        UserModuleOperation entity = new UserModuleOperation();
        entity.setUserNo(Authentication.SUPPER_ADMIN_USER_NO);

        QueryWrapper<UserModuleOperation> qw = new QueryWrapper<>(entity);
        List<UserModuleOperation> list = getService().list(qw);

        UserModuleOperation entity2 = new UserModuleOperation();
        entity2.setUserNo(userNo);
        QueryWrapper<UserModuleOperation> qw2 = new QueryWrapper<>(entity2);
        List<UserModuleOperation> list2 = getService().list(qw2);
        Map<String, Map<String, Boolean>> map2 = new HashMap<>();
        if (!CollectionUtils.isEmpty(list2)) {
            list2.forEach(item -> {
                String operationNos = item.getOperations();
                String[] arr = operationNos.split(",");
                Map<String, Boolean> childrenMap = new HashMap<>();
                for (String operation : arr) {
                    childrenMap.put(operation, true);
                }
                map2.put(item.getModuleNo(), childrenMap);
            });
        }

        List<LayuiTreeVO> respData = new LinkedList<>();
        list.forEach(item -> {
            LayuiTreeVO vo = new LayuiTreeVO();
            vo.setId(item.getModuleNo());
            vo.setTitle(item.getModuleNo());
            vo.setSpread(true);
            if (map2.containsKey(item.getModuleNo())) {
                vo.setChecked(true);
            }
            Map<String, Boolean> childrenMap = map2.get(item.getModuleNo());
            String operationNos = item.getOperations();
            String[] arr = operationNos.split(",");
            List<LayuiTreeVO> children = new LinkedList<>();
            for (String operation : arr) {
                LayuiTreeVO subVo = new LayuiTreeVO();
                subVo.setId(operation);
                subVo.setTitle(operation);
                if (null != childrenMap && childrenMap.containsKey(operation)) {
                    // subVo.setChecked(true);
                }
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
        if (StringUtils.isEmpty(rows)) {
            return new ListResponseVO.Builder<UserModuleOperation>().illegal();
        }

        List<UserModuleOperation> list = JSONArray.parseArray(rows, UserModuleOperation.class);
        String userNo = list.get(0).getUserNo();

        UserModuleOperation entity = new UserModuleOperation();
        entity.setUserNo(userNo);

        QueryWrapper<UserModuleOperation> qw = new QueryWrapper<>(entity);
        // 先删除
        boolean removeFlag = getService().remove(qw);
        if (removeFlag) {
            // 重新插入
            getService().saveBatch(list);
        }
        return new ListResponseVO.Builder<UserModuleOperation>().ok();
    }
    
    @ApiOperation("保存模块用户权限信息")
    @ResponseBody
    @RequestMapping(value = "/saveModuleUserOperations.json", method = { RequestMethod.POST, RequestMethod.GET })
    public ListResponseVO<UserModuleOperation> saveModuleUserOperations(@RequestBody(required=false) RequestVO<UserModuleOperation> reqData) {
        boolean isPass = checkParams(reqData);
        if(!isPass) {
            return new ListResponseVO.Builder<UserModuleOperation>().illegal();
        }
        UserModuleOperation entity = reqData.getEntity();
        String usreNosStr = entity.getUserNo();
        if(StringUtils.isEmpty(usreNosStr)) {
            return new ListResponseVO.Builder<UserModuleOperation>().illegal();
        }
        UpdateWrapper<UserModuleOperation> uwRemove = new UpdateWrapper<>();
        uwRemove.eq("module_no", entity.getModuleNo());
        
        String[] userNosArr = usreNosStr.split(",");
        List<UserModuleOperation> inserted = Lists.newLinkedList();
        List<String> userNosRemove = Lists.newLinkedList();
        for (String userNo : userNosArr) {
            userNosRemove.add(userNo);
            UserModuleOperation umo = new UserModuleOperation();
            umo.setModuleNo(entity.getModuleNo());
            umo.setUserNo(userNo);
            umo.setOperations(entity.getOperations());
            inserted.add(umo);
        }
        uwRemove.in("user_no", userNosRemove);
        // 删除
        getService().remove(uwRemove);
        
        BatchRequestVO<UserModuleOperation> batchReqData = new BatchRequestVO<>();
        batchReqData.setInserted(inserted);
        // 重新插入
        super.batchSave(batchReqData);
        
        ListResponseVO<UserModuleOperation> respData = new ListResponseVO.Builder<UserModuleOperation>().ok();
        respData.setData(inserted);
        
        return respData;
    }
}