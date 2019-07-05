package com.lgfei.code.generator.web.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.lgfei.code.generator.web.filter.CorsPolicyFilter;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<CorsPolicyFilter> registFilter() {
        FilterRegistrationBean<CorsPolicyFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new CorsPolicyFilter());
        registration.addUrlPatterns("/*");
        registration.setOrder(1);
        return registration;
    }
}
