package com.lgfei.code.generator.core.service;

import com.lgfei.code.generator.common.entity.Operation;

import java.util.List;

import com.lgfei.betterme.framework.core.service.IBaseService;

/**
 * <p>
 * 按钮信息表 服务类
 * </p>
 *
 * @author lgfei
 * @since 2019-07-31
 */
public interface IOperationService extends IBaseService<Operation, Long> {

    List<Operation> findUserModuleOpertions(String userNo, String moduleNo);
}
