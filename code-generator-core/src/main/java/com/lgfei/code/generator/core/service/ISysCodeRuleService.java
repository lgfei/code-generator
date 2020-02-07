package com.lgfei.code.generator.core.service;

import com.lgfei.code.generator.common.entity.SysCodeRule;
import com.lgfei.betterme.framework.core.service.IBaseService;

/**
 * <p>
 * 编码规则表 服务类
 * </p>
 *
 * @author lgfei
 * @since 2019-08-25
 */
public interface ISysCodeRuleService extends IBaseService<SysCodeRule, Long> {

    String getNextNo(String noTable, String noField);
}
