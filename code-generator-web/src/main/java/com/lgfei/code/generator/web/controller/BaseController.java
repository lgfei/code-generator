package com.lgfei.code.generator.web.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lgfei.code.generator.model.vo.ResultVO;

import io.swagger.annotations.ApiOperation;

public abstract class BaseController<T> {

	protected abstract IService<T> getService();
	
	@ApiOperation("单个查询")
    @ResponseBody
    @RequestMapping(value = "/one", method = {RequestMethod.POST, RequestMethod.GET})
	public ResultVO<T> one(T t) {
		QueryWrapper<T> queryWrapper = new QueryWrapper<T>(t);
		T data = getService().getOne(queryWrapper);
		ResultVO<T> result = new ResultVO<>();
		result.setData(data);
		return result;
	}
	
	@ApiOperation("查询")
    @ResponseBody
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
	public ResultVO<T> list(T t) {
		QueryWrapper<T> query = new QueryWrapper<T>(t);
		List<T> list = getService().list(query);
		ResultVO<T> result = new ResultVO<>();
		result.setList(list);
		return result;
	}
}
