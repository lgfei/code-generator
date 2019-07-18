package com.lgfei.code.generator.core;

import com.lgfei.code.generator.model.vo.ApiGeneratorParamVO;

public interface ICodeGenerator {
    String TEMPLATE_PATH = "/template/api";

    void generate(ApiGeneratorParamVO paramVo);
}
