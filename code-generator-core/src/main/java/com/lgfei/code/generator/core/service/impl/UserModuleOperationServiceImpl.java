package com.lgfei.code.generator.core.service.impl;

import com.lgfei.code.generator.common.entity.UserModuleOperation;
import com.lgfei.code.generator.core.mapper.UserModuleOperationMapper;
import com.lgfei.code.generator.core.service.IUserModuleOperationService;
import com.lgfei.betterme.framework.core.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户模块关系表 服务实现类
 * </p>
 *
 * @author lgfei
 * @since 2019-07-31
 */
@Service
public class UserModuleOperationServiceImpl extends BaseServiceImpl<UserModuleOperationMapper, UserModuleOperation, Long> 
    implements IUserModuleOperationService {

}
