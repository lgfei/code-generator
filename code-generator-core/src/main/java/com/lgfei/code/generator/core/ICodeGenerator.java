package com.lgfei.code.generator.core;

import com.lgfei.code.generator.common.vo.ApiGeneratorParamVO;

public interface ICodeGenerator {
    String TEMPLATE_PATH = "/template/api";

    void generate(ApiGeneratorParamVO paramVo);
}
