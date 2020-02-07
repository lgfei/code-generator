package com.lgfei.code.generator.core.service.impl;

import com.lgfei.code.generator.common.entity.SysUser;
import com.lgfei.code.generator.core.mapper.SysUserMapper;
import com.lgfei.code.generator.core.service.ISysUserService;
import com.lgfei.betterme.framework.core.service.impl.BaseServiceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author lgfei
 * @since 2019-07-29
 */
@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUserMapper, SysUser, Long> 
    implements ISysUserService {

    @Override
    public List<SysUser> findModuleUsers(String moduleNo) {
        return mapper.findModuleUsers(moduleNo);
    }

}
