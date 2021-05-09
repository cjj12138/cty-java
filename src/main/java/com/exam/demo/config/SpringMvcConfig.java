package com.exam.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@Configuration
public class SpringMvcConfig extends WebMvcConfigurerAdapter {

    private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        // 允许任何的head头部
        corsConfiguration.addAllowedHeader("*");
        // 允许任何域名使用
//        corsConfiguration.addAllowedOrigin("*");
        //cors漏洞
        /*corsConfiguration.addAllowedOrigin("http://10.70.137.223:8080");
        corsConfiguration.addAllowedOrigin("https://10.70.137.223:8080");*/
        /*corsConfiguration.addAllowedOrigin("http://localhost:1003");
        corsConfiguration.addAllowedOrigin("https://aladdin.zj.chinamobile.com");
        corsConfiguration.addAllowedOrigin("http://aladdin.zj.chinamobile.com");*/
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedOrigin("null");
        // 允许任何的请求方法
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.addExposedHeader("Authorization");
        corsConfiguration.setAllowCredentials(true);
        return corsConfiguration;
    }

    /**
     * 添加CorsFilter拦截器，对任意的请求使用
     * @return
     */
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig());
        return new CorsFilter(source);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*").allowCredentials(true)
                .allowedMethods("GET", "POST", "DELETE", "PUT").maxAge(3600);
    }
}
