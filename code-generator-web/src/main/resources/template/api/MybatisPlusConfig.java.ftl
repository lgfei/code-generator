package ${my.groupId}.${package.ModuleName}.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;

/**
 * <p>
 * mybatis-plus配置类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Configuration
public class MybatisPlusConfig
{
    
    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor()
    {
        PaginationInterceptor page = new PaginationInterceptor();
        page.setDialectType("mysql");
        return page;
    }
}
