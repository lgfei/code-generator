package com.lgfei.code.generator.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lgfei.code.generator.core.service.IUserDsRelationService;
import com.lgfei.code.generator.model.entity.UserDsRelation;

import io.swagger.annotations.Api;

@Api(tags = { "用户数据源关系接口" })
@Controller
@RequestMapping("user-ds-relation")
public class UserDsRelationController extends BaseController<UserDsRelation> {

    @Autowired
    private IUserDsRelationService service;

    @Override
    protected IService<UserDsRelation> getService() {
        return service;
    }

}
