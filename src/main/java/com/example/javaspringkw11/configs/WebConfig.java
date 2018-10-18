package com.example.javaspringkw11.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    String path = "file:///"
            +System.getProperty("user.home")
            +File.separator
            +"images"
            +File.separator;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry
                .addResourceHandler("/ava/**")
                .addResourceLocations(path);
    }

}
