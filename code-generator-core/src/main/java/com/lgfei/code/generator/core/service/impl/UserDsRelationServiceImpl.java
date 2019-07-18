package com.lgfei.code.generator.core.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lgfei.code.generator.core.mapper.UserDsRelationMapper;
import com.lgfei.code.generator.core.service.IUserDsRelationService;
import com.lgfei.code.generator.model.entity.UserDsRelation;

@Service
public class UserDsRelationServiceImpl extends ServiceImpl<UserDsRelationMapper, UserDsRelation>
        implements IUserDsRelationService {

}
