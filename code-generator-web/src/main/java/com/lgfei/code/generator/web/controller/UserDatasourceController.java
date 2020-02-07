package com.lgfei.code.generator.web.controller;
 
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lgfei.betterme.framework.api.controller.BaseController;
import com.lgfei.betterme.framework.common.vo.ResponseVO;
import com.lgfei.code.generator.common.entity.UserDatasource;
import com.lgfei.code.generator.core.service.IUserDatasourceService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
 
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
    
    @ApiOperation("保存用户数据源配置")
    @ResponseBody
    @RequestMapping(value = "/saveUserDatasources.json", method = { RequestMethod.POST, RequestMethod.GET })
    public ResponseVO<UserDatasource> saveUserDatasources(@RequestParam("userNo")String userNo, 
            @RequestParam("datasourceNos")String datasourceNos){
        if(StringUtils.isEmpty(userNo)) {
            return new ResponseVO.Builder<UserDatasource>().illegal();
        }
        // 先删除
        UserDatasource entity = new UserDatasource();
        entity.setUserNo(userNo);
        QueryWrapper<UserDatasource> qwRemove = new QueryWrapper<>(entity);
        boolean removeFlag = getService().remove(qwRemove);
        if(removeFlag && !StringUtils.isEmpty(datasourceNos)) {
            // 重新插入
            String[] arr = datasourceNos.split(",");
            UserDatasource tempEntity = new UserDatasource();
            tempEntity.setUserNo(userNo);
            for (String datasourceNo : arr) {
                tempEntity.setDatasourceNo(datasourceNo);
                getService().save(tempEntity);
            }
        }else {
            return new ResponseVO.Builder<UserDatasource>().err();
        }
        return new ResponseVO.Builder<UserDatasource>().ok();
    }
}