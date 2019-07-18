package ${my.parentPackage}.${package.ModuleName}.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.lgfei.betterme.framework.api.ApiApplication;

/**
 * <p>
 * api启动类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@SpringBootApplication
@ComponentScan("${my.parentPackage}.${package.ModuleName}")
@MapperScan("${package.Mapper}")
public class ${my.upperCaseModuleName}ApiApplication extends ApiApplication {
    
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(${my.upperCaseModuleName}ApiApplication.class);
        app.run(args);
        System.err.println("http://127.0.0.1:8080/${my.artifactId}-api/swagger-ui.html");
    }
}
