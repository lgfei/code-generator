package com.lgfei.code.generator.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lgfei.code.generator.common.entity.LogGenerate;
import com.lgfei.code.generator.core.service.ILogGenerateService;

import io.swagger.annotations.Api;

@Api(tags = { "生成日志接口" })
@Controller
@RequestMapping("log-generate")
public class LogGenerateController extends BaseController<LogGenerate> {

    @Autowired
    private ILogGenerateService service;

    @Override
    protected IService<LogGenerate> getService() {
        return service;
    }

}
