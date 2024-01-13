package org.shiki.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
public class WebFilter {
    @Resource
    LoginFilter loginFilter;

    @Bean
    public FilterRegistrationBean<LoginFilter> authFilterBean() {
        FilterRegistrationBean<LoginFilter> frb = new FilterRegistrationBean<>();
        frb.setFilter(loginFilter);
        frb.addUrlPatterns("/*");
        frb.setOrder(1);
        return frb;
    }
}
