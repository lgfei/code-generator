package com.lgfei.code.generator.web.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lgfei.code.generator.model.vo.PageResponseVO;
import com.lgfei.code.generator.model.vo.RequestVO;
import com.lgfei.code.generator.model.vo.ResponseVO;

import io.swagger.annotations.ApiOperation;

public abstract class BaseController<T> {

    protected abstract IService<T> getService();

    @ApiOperation("单个查询")
    @ResponseBody
    @RequestMapping(value = "/one", method = { RequestMethod.POST, RequestMethod.GET })
    public ResponseVO<T> one(RequestVO<T> req) {
        QueryWrapper<T> queryWrapper = new QueryWrapper<T>(req.getEntity());
        T entity = getService().getOne(queryWrapper);

        ResponseVO<T> resp = new ResponseVO<>();
        resp.setEntity(entity);

        return resp;
    }

    @ApiOperation("查询")
    @ResponseBody
    @RequestMapping(value = "/list", method = { RequestMethod.POST, RequestMethod.GET })
    public ResponseVO<T> list(RequestVO<T> req) {
        QueryWrapper<T> queryWrapper = new QueryWrapper<T>(req.getEntity());
        List<T> data = getService().list(queryWrapper);

        ResponseVO<T> resp = new ResponseVO<>();
        resp.setData(data);

        return resp;
    }

    @ApiOperation("分页查询")
    @ResponseBody
    @RequestMapping(value = "/page", method = { RequestMethod.POST, RequestMethod.GET })
    public PageResponseVO<T> page(RequestVO<T> req) {
        QueryWrapper<T> queryWrapper = new QueryWrapper<T>(req.getEntity());
        IPage<T> page = new Page<>(req.getPage(), req.getLimit());
        page = getService().page(page, queryWrapper);

        PageResponseVO<T> resp = new PageResponseVO<T>();
        resp.setCount(page.getTotal());
        resp.setData(page.getRecords());

        return resp;
    }
}
