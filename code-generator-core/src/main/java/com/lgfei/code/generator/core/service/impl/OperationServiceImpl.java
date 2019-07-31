package com.lgfei.code.generator.core.service.impl;

import com.lgfei.code.generator.common.entity.Operation;
import com.lgfei.code.generator.core.mapper.OperationMapper;
import com.lgfei.code.generator.core.service.IOperationService;
import com.lgfei.betterme.framework.core.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

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

}
