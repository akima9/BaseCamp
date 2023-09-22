package com.giyong.community;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class webConfig implements WebMvcConfigurer {

    @Autowired
    private CommonInterceptor commonInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(commonInterceptor)
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin/login")
        ;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("/Users/kgy/Desktop/kgy/study/BaseCamp/src/main/resources/static/");

        registry.addResourceHandler("/basecamp_img/**")
                .addResourceLocations("file:///Users/kgy/Desktop/kgy/study/basecamp_img/");
    }
}
