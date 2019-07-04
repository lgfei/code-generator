package com.lgfei.code.generator.core.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lgfei.code.generator.core.mapper.GenerateLogMapper;
import com.lgfei.code.generator.core.service.IGenerateLogService;
import com.lgfei.code.generator.model.entity.GenerateLog;

@Service
public class GenerateLogSeviceImpl extends ServiceImpl<GenerateLogMapper, GenerateLog> implements IGenerateLogService {

}
