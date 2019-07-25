package com.lgfei.code.generator.core.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lgfei.code.generator.common.entity.LogGenerate;
import com.lgfei.code.generator.core.mapper.LogGenerateMapper;
import com.lgfei.code.generator.core.service.ILogGenerateService;

@Service
public class LogGenerateSeviceImpl extends ServiceImpl<LogGenerateMapper, LogGenerate> implements ILogGenerateService {

}
