package com.lgfei.code.generator.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.ConstVal;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.lgfei.code.generator.common.entity.Datasource;
import com.lgfei.code.generator.common.vo.ApiGeneratorParamVO;
import com.lgfei.code.generator.core.service.IDatasourceService;
import com.lgfei.code.generator.core.util.FileOutConfigUtil;
import com.lgfei.code.generator.core.util.FileUtil;
import com.lgfei.code.generator.core.util.StringUtil;

/**
 * Api代码生成器 <功能详细描述>
 * 
 * @author lgfei
 * @version [版本号, 2019年4月14日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service
public class ApiCodeGenerator implements ICodeGenerator
{
    private static Logger logger = LoggerFactory.getLogger(ApiCodeGenerator.class);
    
    private Datasource datasource;
    
    @Autowired
    private IDatasourceService datasourceService;
    
    private boolean check(ApiGeneratorParamVO paramVo)
    {
        Datasource entity = new Datasource();
        entity.setDsNo(paramVo.getDsNo());
        Wrapper<Datasource> queryWrapper = new QueryWrapper<>(entity);
        Datasource ds = datasourceService.getOne(queryWrapper);
        if (null == ds)
        {
            return false;
        }
        this.datasource = ds;
        return true;
    }
    
    @Override
    public void generate(ApiGeneratorParamVO paramVo)
    {
        boolean isPass = check(paramVo);
        if (!isPass)
        {
            logger.warn("无效参数");
            return;
        }
        // 组装参数
        String dbDriver = datasource.getDriver();// "com.mysql.jdbc.Driver"
        String dbServer = datasource.getServer();// "47.106.134.165";
        String dbPort = String.valueOf(datasource.getPort());// "3306";
        String dbUserName = datasource.getUsername();// "betterme";
        String dbPassword = datasource.getPassword();// "Betterme#1234";
        
        boolean isInit = "on".equals(paramVo.getIsInit()) ? true : false;// true
        String groupId = paramVo.getGroupId();// com.lgfei
        String artifactId = paramVo.getArtifactId();// betterme-admin
        String schemaName = paramVo.getSchemaName();// betterme_admin
        String tableNames = paramVo.getTableNames();// operation_log,user
        String projectPath = paramVo.getProjectPath();// E:\\Test\\code_generator
        
        Map<String, String> myConfig = new HashMap<>();
        myConfig.put("groupId", groupId);
        myConfig.put("artifactId", artifactId);
        myConfig.put("parentPackage", FileOutConfigUtil.getParentPackage(groupId, artifactId));
        // myConfig.put("superManagerClass", "IBaseManager");
        // myConfig.put("superManagerClassPackage",
        // "com.lgfei.betterme.framework.core.manager.IBaseManager");
        // myConfig.put("superManagerImplClass", "BaseManagerImpl");
        // myConfig.put("superManagerImplClassPackage",
        // "com.lgfei.betterme.framework.core.manager.impl.BaseManagerImpl");
        myConfig.put("entityIdClass", "Long");
        myConfig.put("upperCaseModuleName",
            StringUtil.toUpperCaseFirstOne(FileOutConfigUtil.getMouldeName(artifactId)));
        myConfig.put("dbServer", dbServer);
        myConfig.put("dbPort", dbPort);
        myConfig.put("dbName", schemaName);
        myConfig.put("dbUserName", dbUserName);
        myConfig.put("dbPassword", dbPassword);
        
        String[] tableNameArr = tableNames.split(",");
        List<String> entityList = new ArrayList<>();
        for (String tableName : tableNameArr)
        {
            StringBuilder entityName = new StringBuilder();
            String[] arr = tableName.split("_");
            for (String str : arr)
            {
                String temp = StringUtil.toUpperCaseFirstOne(str);
                entityName.append(temp);
            }
            entityList.add(entityName.toString());
        }
        
        // 清理原来已经生成的代码
        if (isInit)
        {
            FileUtil.delFolder(projectPath);
        }
        else
        {
            for (String entity : entityList)
            {
                FileUtil.delFolderFiles(projectPath, entity);
            }
        }
        
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        // gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("lgfei");
        gc.setOpen(false);
        gc.setSwagger2(true);// 实体属性 Swagger2 注解
        
        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(new StringBuilder("jdbc:mysql://").append(dbServer)
            .append(':')
            .append(dbPort)
            .append('/')
            .append(schemaName)
            .append("?useUnicode=true&useSSL=false&characterEncoding=utf8")
            .toString());
        // dsc.setSchemaName("public");
        dsc.setDriverName(dbDriver);
        dsc.setUsername(dbUserName);
        dsc.setPassword(dbPassword);
        
        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(FileOutConfigUtil.getMouldeName(artifactId));
        pc.setParent(FileOutConfigUtil.getParentPackage(groupId, artifactId));
        pc.setEntity("common.entity");
        pc.setMapper("core.mapper");
        pc.setService("core.service");
        pc.setServiceImpl("core.service.impl");
        pc.setController("api.controller");
        Map<String, String> pathInfo = new HashMap<>();
        pathInfo.put(ConstVal.ENTITY_PATH, FileOutConfigUtil.getEntityPath(projectPath, groupId, artifactId));
        pathInfo.put(ConstVal.MAPPER_PATH, FileOutConfigUtil.getMapperPath(projectPath, groupId, artifactId));
        pathInfo.put(ConstVal.XML_PATH, FileOutConfigUtil.getMapperXmlPath(projectPath, artifactId));
        pathInfo.put(ConstVal.SERVICE_PATH, FileOutConfigUtil.getServicePath(projectPath, groupId, artifactId));
        pathInfo.put(ConstVal.SERVICE_IMPL_PATH,
            FileOutConfigUtil.getServiceImplPath(projectPath, groupId, artifactId));
        pathInfo.put(ConstVal.CONTROLLER_PATH, FileOutConfigUtil.getControllerPath(projectPath, groupId, artifactId));
        pc.setPathInfo(pathInfo);
        
        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        // 配置自定义输出模板
        // 指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        templateConfig.setEntity(TEMPLATE_PATH + "/entity.java");
        templateConfig.setMapper(TEMPLATE_PATH + "/mapper.java");
        templateConfig.setXml(TEMPLATE_PATH + "/mapper.xml");
        templateConfig.setService(TEMPLATE_PATH + "/service.java");
        templateConfig.setServiceImpl(TEMPLATE_PATH + "/serviceImpl.java");
        templateConfig.setController(TEMPLATE_PATH + "/controller.java");
        
        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setInclude(tableNames.split(","));
        strategy.setTablePrefix(pc.getModuleName() + "_");
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        // entity
        strategy.setSuperEntityClass("com.lgfei.betterme.framework.common.entity.BaseEntity");
        strategy.setSuperEntityColumns("id",
            "enable_flag",
            "create_user",
            "create_time",
            "update_user",
            "update_time",
            "remark");
        // mapper
        strategy.setSuperMapperClass("com.lgfei.betterme.framework.core.mpper.IBaseMapper");
        // strategy.setEntityLombokModel(true);
        // service
        strategy.setSuperServiceClass("com.lgfei.betterme.framework.core.service.IBaseService");
        strategy.setSuperServiceImplClass("com.lgfei.betterme.framework.core.service.impl.BaseServiceImpl");
        // controller
        strategy.setRestControllerStyle(true);
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setSuperControllerClass("com.lgfei.betterme.framework.api.controller.BaseController");
        
        // 自定义配置
        InjectionConfig cfg = new InjectionConfig()
        {
            @Override
            public void initMap()
            {
                // to do nothing
            }
        };
        // 自定义输出配置(自定义配置会被优先输出)
        List<FileOutConfig> focList = new ArrayList<>();
        // 初始化项目
        if (isInit)
        {
            // 自定义pom的代码模板
            focList.add(FileOutConfigUtil.getPomFileOutConfig(projectPath, TEMPLATE_PATH + "/pom.xml.ftl"));
            // 自定义api-pom的代码模板
            focList.add(
                FileOutConfigUtil.getApiPomFileOutConfig(projectPath, artifactId, TEMPLATE_PATH + "/pom-api.xml.ftl"));
            // 自定义core-pom的代码模板
            focList.add(FileOutConfigUtil
                .getCorePomFileOutConfig(projectPath, artifactId, TEMPLATE_PATH + "/pom-core.xml.ftl"));
            // 自定义common-pom的代码模板
            focList.add(FileOutConfigUtil
                .getCommonPomFileOutConfig(projectPath, artifactId, TEMPLATE_PATH + "/pom-common.xml.ftl"));
            // 自定义ApiApplication的代码模板
            focList.add(FileOutConfigUtil.getApiApplicationFileOutConfig(projectPath,
                groupId,
                artifactId,
                TEMPLATE_PATH + "/ApiApplication.java.ftl"));
            // 自定义MybatisPlusConfig的代码模板
            focList.add(FileOutConfigUtil.getMybatisPlusConfigFileOutConfig(projectPath,
                groupId,
                artifactId,
                TEMPLATE_PATH + "/MybatisPlusConfig.java.ftl"));
            // 自定义Swagger2Config的代码模板
            focList.add(FileOutConfigUtil.getSwagger2ConfigFileOutConfig(projectPath,
                groupId,
                artifactId,
                TEMPLATE_PATH + "/Swagger2Config.java.ftl"));
            // 自定义ControllerAspect.java的代码模板
            focList.add(FileOutConfigUtil.getControllerAspectFileOutConfig(projectPath,
                groupId,
                artifactId,
                TEMPLATE_PATH + "/ControllerAspect.java.ftl"));
            // 自定义application.properties的代码模板
            focList.add(FileOutConfigUtil.getApplicationPropertiesFileOutConfig(projectPath,
                artifactId,
                TEMPLATE_PATH + "/application.properties.ftl"));
            // 自定义application-dev.properties的代码模板
            focList.add(FileOutConfigUtil.getApplicationDevPropertiesFileOutConfig(projectPath,
                artifactId,
                TEMPLATE_PATH + "/application-dev.properties.ftl"));
        }
        cfg.setFileOutConfigList(focList);
        
        mpg.setGlobalConfig(gc);
        mpg.setDataSource(dsc);
        mpg.setPackageInfo(pc);
        mpg.setTemplate(templateConfig);
        mpg.setStrategy(strategy);
        mpg.setCfg(cfg);
        mpg.setTemplateEngine(new MyFreemarkerTemplateEngine(myConfig, entityList));
        mpg.execute();
    }
    
}
