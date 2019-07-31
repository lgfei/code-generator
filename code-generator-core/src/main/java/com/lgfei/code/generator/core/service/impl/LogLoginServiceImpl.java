package com.lgfei.code.generator.core.service.impl;

import com.lgfei.code.generator.common.entity.LogLogin;
import com.lgfei.code.generator.core.mapper.LogLoginMapper;
import com.lgfei.code.generator.core.service.ILogLoginService;
import com.lgfei.betterme.framework.core.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 登录日志表 服务实现类
 * </p>
 *
 * @author lgfei
 * @since 2019-07-31
 */
@Service
public class LogLoginServiceImpl extends BaseServiceImpl<LogLoginMapper, LogLogin, Long> 
    implements ILogLoginService {

}
