package com.lgfei.code.generator.core.service.impl;

import com.lgfei.code.generator.common.entity.LogGenerate;
import com.lgfei.code.generator.core.mapper.LogGenerateMapper;
import com.lgfei.code.generator.core.service.ILogGenerateService;
import com.lgfei.betterme.framework.core.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 生成日志表 服务实现类
 * </p>
 *
 * @author lgfei
 * @since 2019-07-29
 */
@Service
public class LogGenerateServiceImpl extends BaseServiceImpl<LogGenerateMapper, LogGenerate, Long> 
    implements ILogGenerateService {

}
