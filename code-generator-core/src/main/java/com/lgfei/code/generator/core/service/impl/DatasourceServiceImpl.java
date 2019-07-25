package com.lgfei.code.generator.core.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lgfei.code.generator.core.mapper.DatasourceMapper;
import com.lgfei.code.generator.core.service.IDatasourceService;
import com.lgfei.code.generator.common.entity.Datasource;

@Service
public class DatasourceServiceImpl extends ServiceImpl<DatasourceMapper, Datasource> implements IDatasourceService {

}
