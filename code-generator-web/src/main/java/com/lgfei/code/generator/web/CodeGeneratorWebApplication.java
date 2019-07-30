package com.lgfei.code.generator.web;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.annotation.Resource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

@SpringBootApplication
@ConfigurationProperties
@ComponentScan("com.lgfei.code.generator")
@MapperScan("com.lgfei.code.generator.core.mapper")
public class CodeGeneratorWebApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(CodeGeneratorWebApplication.class);
        app.run(args);
        System.err.println("API地址：http://127.0.0.1:8080/code-generator-web/swagger-ui.html");
        System.err.println("首页地址：http://127.0.0.1:8080/code-generator-web/index.htm");
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.thymeleaf.variables")
    protected Properties getThymeleafVariables() {
        return new Properties();
    }

    @Resource
    protected void configureThymeleafStaticVars(ThymeleafViewResolver viewResolver) {
        if (viewResolver != null) {
            Map<String, Object> vars = new HashMap<>();
            Properties properties = getThymeleafVariables();
            Set<Map.Entry<Object, Object>> entrySet = properties.entrySet();
            for (Map.Entry<Object, Object> entry : entrySet) {
                vars.put(String.valueOf(entry.getKey()), entry.getValue());
            }
            viewResolver.setStaticVariables(vars);
        }
    }
}
