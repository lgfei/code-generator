package com.lgfei.code.generator.core.service;

import com.lgfei.code.generator.common.entity.SysUser;

import java.util.List;

import com.lgfei.betterme.framework.core.service.IBaseService;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author lgfei
 * @since 2019-07-29
 */
public interface ISysUserService extends IBaseService<SysUser, Long> {

    List<SysUser> findModuleUsers(String moduleNo);
}
