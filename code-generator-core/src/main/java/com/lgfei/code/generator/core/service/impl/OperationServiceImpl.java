package com.lgfei.code.generator.core.service.impl;

import com.lgfei.code.generator.common.entity.Operation;
import com.lgfei.code.generator.common.entity.UserModuleOperation;
import com.lgfei.code.generator.core.mapper.OperationMapper;
import com.lgfei.code.generator.core.service.IOperationService;
import com.lgfei.code.generator.core.service.IUserModuleOperationService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.lgfei.betterme.framework.core.service.impl.BaseServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 按钮信息表 服务实现类
 * </p>
 *
 * @author lgfei
 * @since 2019-07-31
 */
@Service
public class OperationServiceImpl extends BaseServiceImpl<OperationMapper, Operation, Long> 
    implements IOperationService {
    
    @Autowired
    private IUserModuleOperationService userModuleOperationService;

    @Override
    public List<Operation> findUserModuleOpertions(String userNo, String moduleNo) {
        UserModuleOperation umo = new UserModuleOperation();
        umo.setUserNo(userNo);
        umo.setModuleNo(moduleNo);
        QueryWrapper<UserModuleOperation> qwUmo = new QueryWrapper<>(umo);
        List<UserModuleOperation> umos = userModuleOperationService.list(qwUmo);
        if(CollectionUtils.isEmpty(umos)) {
           return new ArrayList<>(); 
        }
        umo = umos.get(0);
        
        String operationsStr = umo.getOperations();
        if(StringUtils.isEmpty(operationsStr)) {
            return new ArrayList<>(); 
        }
        String[]  operationsArr = operationsStr.split(",");
        List<String> operationsList = Lists.newArrayList(operationsArr);
        QueryWrapper<Operation> qwOpt = new QueryWrapper<>();
        qwOpt.in("value", operationsList);
        
        return getMapper().selectList(qwOpt);
    }

}
