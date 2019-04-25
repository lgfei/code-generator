package com.lgfei.code.generator.core;

import com.lgfei.code.generator.model.ParamVO;

public interface ICodeGenerator
{
    String TEMPLATE_PATH = "/template/api";
    
    boolean check(ParamVO paramVO);
    
    void generate(ParamVO paramVO);
}
