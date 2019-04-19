package com.lgfei.code.generator.core.util;

import java.io.File;

import org.springframework.util.StringUtils;

import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;

public final class FileOutConfigUtil
{
    private static String parentPackage = "com/xxx/project";
    
    private static String handleGroupId(String groupId)
    {
        if (StringUtils.isEmpty(groupId))
        {
            return "com.xxx.project";
        }
        parentPackage = groupId.replace(".", "/");
        return groupId;
    }
    
    public static String getEntityPath(String projectPath, String moduleName, String groupId, String artifactId)
    {
        groupId = handleGroupId(groupId);
        StringBuilder path = new StringBuilder(projectPath).append('/')
            .append(artifactId)
            .append("-model")
            .append("/src/main/java/")
            .append(parentPackage)
            .append('/')
            .append(moduleName)
            .append("/model")
            .append("/entity");
        return path.toString();
    }
    
    public static String getMapperPath(String projectPath, String moduleName, String groupId, String artifactId)
    {
        groupId = handleGroupId(groupId);
        StringBuilder path = new StringBuilder(projectPath).append('/')
            .append(artifactId)
            .append("-core")
            .append("/src/main/java/")
            .append(parentPackage)
            .append('/')
            .append(moduleName)
            .append("/core")
            .append("/mapper");
        return path.toString();
    }
    
    public static String getMapperXmlPath(String projectPath, String artifactId)
    {
        StringBuilder path = new StringBuilder(projectPath).append('/')
            .append(artifactId)
            .append("-core")
            .append("/src/main/resources/")
            .append("/mapper");
        return path.toString();
    }
    
    public static String getServicePath(String projectPath, String moduleName, String groupId, String artifactId)
    {
        groupId = handleGroupId(groupId);
        StringBuilder path = new StringBuilder(projectPath).append('/')
            .append(artifactId)
            .append("-core")
            .append("/src/main/java/")
            .append(parentPackage)
            .append('/')
            .append(moduleName)
            .append("/core")
            .append("/service");
        return path.toString();
    }
    
    public static String getServiceImplPath(String projectPath, String moduleName, String groupId, String artifactId)
    {
        groupId = handleGroupId(groupId);
        StringBuilder path = new StringBuilder(projectPath).append('/')
            .append(artifactId)
            .append("-core")
            .append("/src/main/java/")
            .append(parentPackage)
            .append('/')
            .append(moduleName)
            .append("/core")
            .append("/service")
            .append("/impl");
        return path.toString();
    }
    
    public static String getControllerPath(String projectPath, String moduleName, String groupId, String artifactId)
    {
        groupId = handleGroupId(groupId);
        StringBuilder path = new StringBuilder(projectPath).append('/')
            .append(artifactId)
            .append("-api")
            .append("/src/main/java/")
            .append(parentPackage)
            .append('/')
            .append(moduleName)
            .append("/api")
            .append("/controller");
        return path.toString();
    }
    
    public static FileOutConfig getManagerFileOutConfig(String projectPath, String moduleName, String groupId,
        String artifactId, String templatePath)
    {
        groupId = handleGroupId(groupId);
        if (StringUtils.isEmpty(templatePath))
        {
            templatePath = "/template/manager.java.ftl";
        }
        return new FileOutConfig(templatePath)
        {
            @Override
            public String outputFile(TableInfo tableInfo)
            {
                return new StringBuilder(projectPath).append('/')
                    .append(artifactId)
                    .append("-core")
                    .append("/src/main/java/")
                    .append(parentPackage)
                    .append('/')
                    .append(moduleName)
                    .append("/core")
                    .append("/manager")
                    .append(File.separator)
                    .append('I')
                    .append(tableInfo.getEntityName())
                    .append("Manager.java")
                    .toString();
            }
        };
    }
    
    public static FileOutConfig getManagerImplFileOutConfig(String projectPath, String moduleName, String groupId,
        String artifactId, String templatePath)
    {
        groupId = handleGroupId(groupId);
        if (StringUtils.isEmpty(templatePath))
        {
            templatePath = "/template/managerImpl.java.ftl";
        }
        return new FileOutConfig(templatePath)
        {
            @Override
            public String outputFile(TableInfo tableInfo)
            {
                return new StringBuilder(projectPath).append('/')
                    .append(artifactId)
                    .append("-core")
                    .append("/src/main/java/")
                    .append(parentPackage)
                    .append('/')
                    .append(moduleName)
                    .append("/core")
                    .append("/manager")
                    .append("/impl")
                    .append(File.separator)
                    .append(tableInfo.getEntityName())
                    .append("ManagerImpl.java")
                    .toString();
            }
        };
    }
    
    public static FileOutConfig getPomFileOutConfig(String projectPath, String templatePath)
    {
        if (StringUtils.isEmpty(templatePath))
        {
            templatePath = "/template/pom.xml.ftl";
        }
        return new FileOutConfig(templatePath)
        {
            @Override
            public String outputFile(TableInfo tableInfo)
            {
                return new StringBuilder(projectPath).append(File.separator).append("pom.xml").toString();
            }
        };
    }
    
    public static FileOutConfig getApiPomFileOutConfig(String projectPath, String artifactId, String templatePath)
    {
        if (StringUtils.isEmpty(templatePath))
        {
            templatePath = "/template/pom-api.xml.ftl";
        }
        return new FileOutConfig(templatePath)
        {
            @Override
            public String outputFile(TableInfo tableInfo)
            {
                return new StringBuilder(projectPath).append('/')
                    .append(artifactId)
                    .append("-api")
                    .append(File.separator)
                    .append("pom.xml")
                    .toString();
            }
        };
    }
    
    public static FileOutConfig getCorePomFileOutConfig(String projectPath, String artifactId, String templatePath)
    {
        if (StringUtils.isEmpty(templatePath))
        {
            templatePath = "/template/pom-core.xml.ftl";
        }
        return new FileOutConfig(templatePath)
        {
            @Override
            public String outputFile(TableInfo tableInfo)
            {
                return new StringBuilder(projectPath).append('/')
                    .append(artifactId)
                    .append("-core")
                    .append(File.separator)
                    .append("pom.xml")
                    .toString();
            }
        };
    }
    
    public static FileOutConfig getModelPomFileOutConfig(String projectPath, String artifactId, String templatePath)
    {
        if (StringUtils.isEmpty(templatePath))
        {
            templatePath = "/template/pom-model.xml.ftl";
        }
        return new FileOutConfig(templatePath)
        {
            @Override
            public String outputFile(TableInfo tableInfo)
            {
                return new StringBuilder(projectPath).append('/')
                    .append(artifactId)
                    .append("-model")
                    .append(File.separator)
                    .append("pom.xml")
                    .toString();
            }
        };
    }
    
    public static FileOutConfig getApiApplicationFileOutConfig(String projectPath, String moduleName, String groupId,
        String artifactId, String templatePath)
    {
        groupId = handleGroupId(groupId);
        if (StringUtils.isEmpty(templatePath))
        {
            templatePath = "/template/ApiApplication.java.ftl";
        }
        return new FileOutConfig(templatePath)
        {
            @Override
            public String outputFile(TableInfo tableInfo)
            {
                return new StringBuilder(projectPath).append('/')
                    .append(artifactId)
                    .append("-api")
                    .append("/src/main/java/")
                    .append(parentPackage)
                    .append('/')
                    .append(moduleName)
                    .append("/api")
                    .append(File.separator)
                    .append(StringUtil.toUpperCaseFirstOne(moduleName))
                    .append("ApiApplication.java")
                    .toString();
            }
        };
    }
    
    public static FileOutConfig getMybatisPlusConfigFileOutConfig(String projectPath, String moduleName, String groupId,
        String artifactId, String templatePath)
    {
        groupId = handleGroupId(groupId);
        if (StringUtils.isEmpty(templatePath))
        {
            templatePath = "/template/MybatisPlusConfig.java.ftl";
        }
        return new FileOutConfig(templatePath)
        {
            @Override
            public String outputFile(TableInfo tableInfo)
            {
                return new StringBuilder(projectPath).append('/')
                    .append(artifactId)
                    .append("-api")
                    .append("/src/main/java/")
                    .append(parentPackage)
                    .append('/')
                    .append(moduleName)
                    .append("/api/config")
                    .append(File.separator)
                    .append("MybatisPlusConfig.java")
                    .toString();
            }
        };
    }
    
    public static FileOutConfig getSwagger2ConfigFileOutConfig(String projectPath, String moduleName, String groupId,
        String artifactId, String templatePath)
    {
        groupId = handleGroupId(groupId);
        if (StringUtils.isEmpty(templatePath))
        {
            templatePath = "/template/Swagger2Config.java.ftl";
        }
        return new FileOutConfig(templatePath)
        {
            @Override
            public String outputFile(TableInfo tableInfo)
            {
                return new StringBuilder(projectPath).append('/')
                    .append(artifactId)
                    .append("-api")
                    .append("/src/main/java/")
                    .append(parentPackage)
                    .append('/')
                    .append(moduleName)
                    .append("/api/config")
                    .append(File.separator)
                    .append("Swagger2Config.java")
                    .toString();
            }
        };
    }
    
    public static FileOutConfig getApplicationPropertiesFileOutConfig(String projectPath, String artifactId,
        String templatePath)
    {
        if (StringUtils.isEmpty(templatePath))
        {
            templatePath = "/template/application.properties.ftl";
        }
        return new FileOutConfig(templatePath)
        {
            @Override
            public String outputFile(TableInfo tableInfo)
            {
                return new StringBuilder(projectPath).append('/')
                    .append(artifactId)
                    .append("-api")
                    .append("/src/main/resources/")
                    .append("application.properties")
                    .toString();
            }
        };
    }
    
    public static FileOutConfig getApplicationDevPropertiesFileOutConfig(String projectPath, String artifactId,
        String templatePath)
    {
        if (StringUtils.isEmpty(templatePath))
        {
            templatePath = "/template/application-dev.properties.ftl";
        }
        return new FileOutConfig(templatePath)
        {
            @Override
            public String outputFile(TableInfo tableInfo)
            {
                return new StringBuilder(projectPath).append('/')
                    .append(artifactId)
                    .append("-api")
                    .append("/src/main/resources/")
                    .append("application-dev.properties")
                    .toString();
            }
        };
    }
    
    public static FileOutConfig getMybatisConfigXmlFileOutConfig(String projectPath, String artifactId,
        String templatePath)
    {
        if (StringUtils.isEmpty(templatePath))
        {
            templatePath = "/template/mybatis-config.xml.ftl";
        }
        return new FileOutConfig(templatePath)
        {
            @Override
            public String outputFile(TableInfo tableInfo)
            {
                return new StringBuilder(projectPath).append('/')
                    .append(artifactId)
                    .append("-api")
                    .append("/src/main/resources/")
                    .append("mybatis-config.xml")
                    .toString();
            }
        };
    }
    
}
