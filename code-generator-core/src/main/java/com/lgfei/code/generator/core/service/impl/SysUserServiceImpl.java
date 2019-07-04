package com.lgfei.code.generator.core.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lgfei.code.generator.core.mapper.SysUserMapper;
import com.lgfei.code.generator.core.service.ISysUserService;
import com.lgfei.code.generator.model.entity.SysUser;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

}
