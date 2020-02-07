package com.lgfei.code.generator.core.service.impl;

import com.lgfei.code.generator.common.entity.UserDatasource;
import com.lgfei.code.generator.core.mapper.UserDatasourceMapper;
import com.lgfei.code.generator.core.service.IUserDatasourceService;
import com.lgfei.betterme.framework.core.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户数据源关系表 服务实现类
 * </p>
 *
 * @author lgfei
 * @since 2019-07-31
 */
@Service
public class UserDatasourceServiceImpl extends BaseServiceImpl<UserDatasourceMapper, UserDatasource, Long> 
    implements IUserDatasourceService {

}
