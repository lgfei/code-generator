package com.lgfei.code.generator.core;

import com.lgfei.code.generator.model.entity.Datasource;
import com.lgfei.code.generator.model.vo.TableParamVO;

public interface ICodeGenerator
{
    String TEMPLATE_PATH = "/template/api";
    
    boolean check(Datasource ds, TableParamVO tableParamVO);
    
    void generate(Datasource ds, TableParamVO tableParamVO);
}
