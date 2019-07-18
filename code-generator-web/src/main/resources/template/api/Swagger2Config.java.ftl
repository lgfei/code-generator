package ${my.parentPackage}.${package.ModuleName}.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * <p>
 * Swagger配置类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {
    
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
            .select()
            .apis(RequestHandlerSelectors.basePackage("${my.parentPackage}.${package.ModuleName}.api"))
            .paths(PathSelectors.any())
            .build();
    }
    
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("${my.artifactId} APIs")
            .description("${my.artifactId} APIs")
            .termsOfServiceUrl("http://${author}.com")
            .contact(new Contact("${author}", "http://${author}.com", "email@xxx.com"))
            .version("1.0.0")
            .build();
    }
    
}
