package com.lgfei.code.generator.web.controller;
 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

import com.lgfei.code.generator.common.entity.UserDatasource;
import com.lgfei.code.generator.core.service.IUserDatasourceService;

import com.lgfei.betterme.framework.api.controller.BaseController;
 
/**
 * <p>
 * 用户数据源关系表 前端控制器
 * </p>
 *
 * @author lgfei
 * @since 2019-07-31
 */
@Api(tags = {"用户数据源关系表"})
@RestController
@RequestMapping("/user-datasource")
public class  UserDatasourceController extends BaseController<IUserDatasourceService, UserDatasource, Long> {
    
    @Override
    protected UserDatasource newEntity() {
        return new UserDatasource();
	}
}