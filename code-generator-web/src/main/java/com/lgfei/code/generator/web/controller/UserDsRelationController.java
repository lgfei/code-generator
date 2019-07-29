package com.lgfei.code.generator.web.controller;
 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

import com.lgfei.code.generator.common.entity.UserDsRelation;
import com.lgfei.code.generator.core.service.IUserDsRelationService;

import com.lgfei.betterme.framework.api.controller.BaseController;
 
/**
 * <p>
 * 用户数据源关系表 前端控制器
 * </p>
 *
 * @author lgfei
 * @since 2019-07-29
 */
@Api(tags = {"用户数据源关系表"})
@RestController
@RequestMapping("/user-ds-relation")
public class  UserDsRelationController extends BaseController<IUserDsRelationService,UserDsRelation, Long> {
    
    @Override
    protected UserDsRelation newEntity() {
        return new UserDsRelation();
	}
}