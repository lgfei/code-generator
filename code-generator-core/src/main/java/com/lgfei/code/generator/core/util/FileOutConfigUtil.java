package com.lgfei.code.generator.core.util;

import java.io.File;

import org.springframework.util.StringUtils;

import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;

public final class FileOutConfigUtil {
    public static String getParentPackage(String groupId, String artifactId) {
        StringBuilder sb = new StringBuilder();
        // 处理groupId
        if (StringUtils.isEmpty(groupId)) {
            groupId = "group";
        }
        sb.append(groupId);
        // 处理artifactId
        if (StringUtils.isEmpty(artifactId)) {
            artifactId = "project-module";
        }
        String[] arr = artifactId.split("-");
        if (arr.length > 1) {
            sb.append('.').append(arr[0]);
        }
        return sb.toString();
    }

    public static String getParentPath(String groupId, String artifactId) {
        StringBuilder sb = new StringBuilder();
        // 处理groupId
        if (StringUtils.isEmpty(groupId)) {
            groupId = "group";
        }
        String[] groupIdArr = groupId.split("\\.");
        for (String str : groupIdArr) {
            sb.append(str).append('/');
        }
        // 处理artifactId
        if (StringUtils.isEmpty(artifactId)) {
            artifactId = "project-module";
        }
        String[] artifactIdArr = artifactId.split("-");
        int count = 0;
        for (String str : artifactIdArr) {
            count++;
            if (count < artifactIdArr.length) {
                sb.append(str).append('/');
            } else {
                sb.append(str);
            }
        }

        return sb.toString();
    }

    public static String getMouldeName(String artifactId) {
        if (StringUtils.isEmpty(artifactId)) {
            artifactId = "project-module";
        }
        String[] arr = artifactId.split("-");
        if (arr.length == 1) {
            return arr[0];
        } else if (arr.length == 2) {
            return arr[1];
        } else {
            StringBuilder tmp = new StringBuilder();
            for (int i = 2; i < arr.length; i++) {
                tmp.append(arr[i]);
            }
            return tmp.toString();
        }
    }

    public static String getEntityPath(String projectPath, String groupId, String artifactId) {
        String parentPackage = getParentPath(groupId, artifactId);
        StringBuilder path = new StringBuilder(projectPath).append('/').append(artifactId).append("-model")
                .append("/src/main/java/").append(parentPackage).append("/model").append("/entity");
        return path.toString();
    }

    public static String getMapperPath(String projectPath, String groupId, String artifactId) {
        String parentPackage = getParentPath(groupId, artifactId);
        StringBuilder path = new StringBuilder(projectPath).append('/').append(artifactId).append("-core")
                .append("/src/main/java/").append(parentPackage).append("/core").append("/mapper");
        return path.toString();
    }

    public static String getMapperXmlPath(String projectPath, String artifactId) {
        StringBuilder path = new StringBuilder(projectPath).append('/').append(artifactId).append("-core")
                .append("/src/main/resources").append("/mapper").append("/default");
        return path.toString();
    }

    public static String getServicePath(String projectPath, String groupId, String artifactId) {
        String parentPackage = getParentPath(groupId, artifactId);
        StringBuilder path = new StringBuilder(projectPath).append('/').append(artifactId).append("-core")
                .append("/src/main/java/").append(parentPackage).append("/core").append("/service");
        return path.toString();
    }

    public static String getServiceImplPath(String projectPath, String groupId, String artifactId) {
        String parentPackage = getParentPath(groupId, artifactId);
        StringBuilder path = new StringBuilder(projectPath).append('/').append(artifactId).append("-core")
                .append("/src/main/java/").append(parentPackage).append("/core").append("/service").append("/impl");
        return path.toString();
    }

    public static String getControllerPath(String projectPath, String groupId, String artifactId) {
        String parentPackage = getParentPath(groupId, artifactId);
        StringBuilder path = new StringBuilder(projectPath).append('/').append(artifactId).append("-api")
                .append("/src/main/java/").append(parentPackage).append("/api").append("/controller");
        return path.toString();
    }

    public static FileOutConfig getManagerFileOutConfig(String projectPath, String groupId, String artifactId,
            String templatePath) {
        String parentPackage = getParentPath(groupId, artifactId);
        if (StringUtils.isEmpty(templatePath)) {
            templatePath = "/template/manager.java.ftl";
        }
        return new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return new StringBuilder(projectPath).append('/').append(artifactId).append("-core")
                        .append("/src/main/java/").append(parentPackage).append("/core").append("/manager")
                        .append(File.separator).append('I').append(tableInfo.getEntityName()).append("Manager.java")
                        .toString();
            }
        };
    }

    public static FileOutConfig getManagerImplFileOutConfig(String projectPath, String groupId, String artifactId,
            String templatePath) {
        String parentPackage = getParentPath(groupId, artifactId);
        if (StringUtils.isEmpty(templatePath)) {
            templatePath = "/template/managerImpl.java.ftl";
        }
        return new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return new StringBuilder(projectPath).append('/').append(artifactId).append("-core")
                        .append("/src/main/java/").append(parentPackage).append("/core").append("/manager")
                        .append("/impl").append(File.separator).append(tableInfo.getEntityName())
                        .append("ManagerImpl.java").toString();
            }
        };
    }

    public static FileOutConfig getPomFileOutConfig(String projectPath, String templatePath) {
        if (StringUtils.isEmpty(templatePath)) {
            templatePath = "/template/pom.xml.ftl";
        }
        return new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return new StringBuilder(projectPath).append(File.separator).append("pom.xml").toString();
            }
        };
    }

    public static FileOutConfig getApiPomFileOutConfig(String projectPath, String artifactId, String templatePath) {
        if (StringUtils.isEmpty(templatePath)) {
            templatePath = "/template/pom-api.xml.ftl";
        }
        return new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return new StringBuilder(projectPath).append('/').append(artifactId).append("-api")
                        .append(File.separator).append("pom.xml").toString();
            }
        };
    }

    public static FileOutConfig getCorePomFileOutConfig(String projectPath, String artifactId, String templatePath) {
        if (StringUtils.isEmpty(templatePath)) {
            templatePath = "/template/pom-core.xml.ftl";
        }
        return new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return new StringBuilder(projectPath).append('/').append(artifactId).append("-core")
                        .append(File.separator).append("pom.xml").toString();
            }
        };
    }

    public static FileOutConfig getModelPomFileOutConfig(String projectPath, String artifactId, String templatePath) {
        if (StringUtils.isEmpty(templatePath)) {
            templatePath = "/template/pom-model.xml.ftl";
        }
        return new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return new StringBuilder(projectPath).append('/').append(artifactId).append("-model")
                        .append(File.separator).append("pom.xml").toString();
            }
        };
    }

    public static FileOutConfig getApiApplicationFileOutConfig(String projectPath, String groupId, String artifactId,
            String templatePath) {
        String parentPackage = getParentPath(groupId, artifactId);
        if (StringUtils.isEmpty(templatePath)) {
            templatePath = "/template/ApiApplication.java.ftl";
        }
        return new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return new StringBuilder(projectPath).append('/').append(artifactId).append("-api")
                        .append("/src/main/java/").append(parentPackage).append("/api").append(File.separator)
                        .append(StringUtil.toUpperCaseFirstOne(getMouldeName(artifactId))).append("ApiApplication.java")
                        .toString();
            }
        };
    }

    public static FileOutConfig getMybatisPlusConfigFileOutConfig(String projectPath, String groupId, String artifactId,
            String templatePath) {
        String parentPackage = getParentPath(groupId, artifactId);
        if (StringUtils.isEmpty(templatePath)) {
            templatePath = "/template/MybatisPlusConfig.java.ftl";
        }
        return new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return new StringBuilder(projectPath).append('/').append(artifactId).append("-api")
                        .append("/src/main/java/").append(parentPackage).append("/api/config").append(File.separator)
                        .append("MybatisPlusConfig.java").toString();
            }
        };
    }

    public static FileOutConfig getSwagger2ConfigFileOutConfig(String projectPath, String groupId, String artifactId,
            String templatePath) {
        String parentPackage = getParentPath(groupId, artifactId);
        if (StringUtils.isEmpty(templatePath)) {
            templatePath = "/template/Swagger2Config.java.ftl";
        }
        return new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return new StringBuilder(projectPath).append('/').append(artifactId).append("-api")
                        .append("/src/main/java/").append(parentPackage).append("/api/config").append(File.separator)
                        .append("Swagger2Config.java").toString();
            }
        };
    }

    public static FileOutConfig getApplicationPropertiesFileOutConfig(String projectPath, String artifactId,
            String templatePath) {
        if (StringUtils.isEmpty(templatePath)) {
            templatePath = "/template/application.properties.ftl";
        }
        return new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return new StringBuilder(projectPath).append('/').append(artifactId).append("-api")
                        .append("/src/main/resources/").append("application.properties").toString();
            }
        };
    }

    public static FileOutConfig getApplicationDevPropertiesFileOutConfig(String projectPath, String artifactId,
            String templatePath) {
        if (StringUtils.isEmpty(templatePath)) {
            templatePath = "/template/application-dev.properties.ftl";
        }
        return new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return new StringBuilder(projectPath).append('/').append(artifactId).append("-api")
                        .append("/src/main/resources/").append("application-dev.properties").toString();
            }
        };
    }

    public static FileOutConfig getMybatisConfigXmlFileOutConfig(String projectPath, String artifactId,
            String templatePath) {
        if (StringUtils.isEmpty(templatePath)) {
            templatePath = "/template/mybatis-config.xml.ftl";
        }
        return new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return new StringBuilder(projectPath).append('/').append(artifactId).append("-api")
                        .append("/src/main/resources/").append("mybatis-config.xml").toString();
            }
        };
    }

}
