package com.lgfei.code.generator.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lgfei.betterme.framework.api.controller.BaseController;
import com.lgfei.code.generator.common.entity.SysUser;
import com.lgfei.code.generator.core.security.Authentication;
import com.lgfei.code.generator.core.service.ISysCodeRuleService;
import com.lgfei.code.generator.core.service.ISysUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author lgfei
 * @since 2019-07-29
 */
@Api(tags = { "用户信息表" })
@Controller
@RequestMapping("/sys-user")
public class SysUserController extends BaseController<ISysUserService, SysUser, Long> {

    @Autowired
    private ISysCodeRuleService sysCodeRuleService;

    @Override
    protected SysUser newEntity() {
        return new SysUser();
    }

    @RequestMapping(value = "/index.htm", method = RequestMethod.GET)
    public String gotoIndexView() {
        return "sys-user/index";
    }

    @RequestMapping(value = "/add.htm", method = RequestMethod.GET)
    public String gotoAddView() {
        return "sys-user/add";
    }

    @Override
    protected SysUser fillNo(SysUser entity) {
        if (null == entity || !StringUtils.isEmpty(entity.getUserNo())) {
            return entity;
        }
        String nextNo = sysCodeRuleService.getNextNo("sys_user", "user_no");
        if (!StringUtils.isEmpty(nextNo)) {
            entity.setUserNo(nextNo);
        }
        return entity;
    }

    @ApiOperation("模块分配用户查询接口")
    @ResponseBody
    @RequestMapping(value = "/findModuleUsers.json", method = { RequestMethod.POST, RequestMethod.GET })
    public Map<String, List<SysUser>> findModuleUsers(@RequestParam(value = "moduleNo") String moduleNo) {
        Map<String, List<SysUser>> respData = new HashMap<>(2);

        QueryWrapper<SysUser> qw = new QueryWrapper<>();
        qw.ne("user_no", Authentication.SUPPER_ADMIN_USER_NO);
        List<SysUser> itemsList = getService().list(qw);

        List<SysUser> selectedList = service.findModuleUsers(moduleNo);

        respData.put("selected", selectedList);
        respData.put("items", itemsList);

        return respData;
    }
}