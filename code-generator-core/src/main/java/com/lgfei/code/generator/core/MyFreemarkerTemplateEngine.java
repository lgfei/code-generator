package com.lgfei.code.generator.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

public class MyFreemarkerTemplateEngine extends FreemarkerTemplateEngine {
    private Map<String, String> my;

    private List<String> entityList;

    public MyFreemarkerTemplateEngine() {

    }

    public MyFreemarkerTemplateEngine(Map<String, String> myConfig, List<String> entityList) {
        this.my = myConfig;
        this.entityList = entityList;
    }

    @Override
    public void writer(Map<String, Object> objectMap, String templatePath, String outputFile) throws Exception {
        if (null == this.my) {
            this.my = new HashMap<>();
        }
        TableInfo table = (TableInfo) objectMap.get("table");
        my.put("managerName", "I" + table.getEntityName() + "Manager");
        my.put("managerImplName", table.getEntityName() + "ManagerImpl");

        @SuppressWarnings("unchecked")
        Map<String, String> _package = (Map<String, String>) objectMap.get("package");
        StringBuilder managerPackage = new StringBuilder();
        String[] entityPackages = _package.get("Entity").split("\\.");
        for (String str : entityPackages) {
            if (!"model".equals(str)) {
                managerPackage.append(str).append(StringPool.DOT);
            } else {
                break;
            }
        }
        managerPackage.append("core").append(StringPool.DOT).append("manager");
        StringBuilder managerImplPackage = new StringBuilder(managerPackage).append(StringPool.DOT).append("impl");
        my.put("Manager", managerPackage.toString());
        my.put("ManagerImpl", managerImplPackage.toString());

        objectMap.put("my", this.my);
        objectMap.put("entityList", this.entityList);

        super.writer(objectMap, templatePath, outputFile);
    }

    public Map<String, String> getMy() {
        return my;
    }

    public void setMy(Map<String, String> my) {
        this.my = my;
    }

    public List<String> getEntityList() {
        return entityList;
    }

    public void setEntityList(List<String> entityList) {
        this.entityList = entityList;
    }

}
