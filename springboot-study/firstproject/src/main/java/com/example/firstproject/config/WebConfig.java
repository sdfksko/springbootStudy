package com.example.firstproject.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // '/resources/**'로 들어오는 요청은 'classpath:/statice/'에서 처리하도록 설정
        registry.addResourceHandler("/resources/**")    // 호출되는 url path 패턴
                .addResourceLocations("classpath:/static/")         // 실제 리소스 위치
                .setCachePeriod(1); // 캐시 기간 설정(단위는 초)
    }

}
