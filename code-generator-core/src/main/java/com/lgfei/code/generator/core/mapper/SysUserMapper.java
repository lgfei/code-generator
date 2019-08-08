package com.lgfei.code.generator.core.mapper;

import com.lgfei.code.generator.common.entity.SysUser;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lgfei.betterme.framework.core.mpper.IBaseMapper;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author lgfei
 * @since 2019-07-29
 */
public interface SysUserMapper extends IBaseMapper<SysUser, Long> {

    List<SysUser> findModuleUsers(@Param("moduleNo") String moduleNo);
}
