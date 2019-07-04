package com.lgfei.code.generator.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lgfei.code.generator.core.service.IGenerateLogService;
import com.lgfei.code.generator.model.entity.GenerateLog;

import io.swagger.annotations.Api;

@Api(tags = {"生成日志接口"})
@Controller
@RequestMapping("generate-log")
public class GenerateLogController extends BaseController<GenerateLog> {

	@Autowired
	private IGenerateLogService service;
	
	@Override
	protected IService<GenerateLog> getService() {
		return service;
	}

}
