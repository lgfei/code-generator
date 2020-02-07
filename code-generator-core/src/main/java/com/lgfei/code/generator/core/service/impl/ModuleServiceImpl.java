package com.lgfei.code.generator.core.service.impl;

import com.lgfei.code.generator.common.entity.Module;
import com.lgfei.code.generator.core.mapper.ModuleMapper;
import com.lgfei.code.generator.core.service.IModuleService;
import com.lgfei.betterme.framework.core.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 模块信息表 服务实现类
 * </p>
 *
 * @author lgfei
 * @since 2019-07-29
 */
@Service
public class ModuleServiceImpl extends BaseServiceImpl<ModuleMapper, Module, Long> 
    implements IModuleService {

}
