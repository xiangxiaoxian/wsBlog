package com.xiang.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName WebMvcConfig.java
 * @Description TODO
 * @createTime 2021年03月30日 20:46:00
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/file/**").addResourceLocations("file:E:/wsBlogArticle/");
        super.addResourceHandlers(registry);
    }
}
