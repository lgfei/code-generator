package com.lgfei.code.generator.core.service.impl;

import com.lgfei.code.generator.common.entity.Datasource;
import com.lgfei.code.generator.core.mapper.DatasourceMapper;
import com.lgfei.code.generator.core.service.IDatasourceService;
import com.lgfei.betterme.framework.core.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 数据源信息表 服务实现类
 * </p>
 *
 * @author lgfei
 * @since 2019-07-29
 */
@Service
public class DatasourceServiceImpl extends BaseServiceImpl<DatasourceMapper, Datasource, Long> 
    implements IDatasourceService {

}
