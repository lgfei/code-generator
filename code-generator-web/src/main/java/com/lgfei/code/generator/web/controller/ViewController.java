package com.lgfei.code.generator.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.Api;

@Api(tags = { "页面接口" })
@Controller
@RequestMapping("")
public class ViewController {
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main() {
        return "main";
    }

    @RequestMapping(value = "/api-code-generator", method = RequestMethod.GET)
    public String apiCodeGenerator() {
        return "api-code-generator";
    }
}
