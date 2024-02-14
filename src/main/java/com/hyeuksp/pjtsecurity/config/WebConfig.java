package com.hyeuksp.pjtsecurity.config;

/**
 * Copyright (c) 2018, Software in Life, Inc. All rights reserved.
 */
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.TimeUnit;

/**
 * <b>Spring Date Web Support Configuration</b>
 * <p>
 * Spring Data Web Support 설정은 Spring Data에서 사용하는 Pageable이나 Sort 등의 기능을 확장해서
 * 사용하기 위한 설정입니다.
 * </p>
 */
@Configuration
@EnableSpringDataWebSupport
public class WebConfig implements WebMvcConfigurer {
    private static final Logger logger = LoggerFactory.getLogger(MvcConfig.class);
    @Bean
    public WebMvcConfigurer CORSConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedHeaders("*")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD", "OPTIONS")
                        .maxAge(-1)   // add maxAge
                        .allowCredentials(false);
            }
        };
    }
    /**
     * @author Kim, Young-suk <youngsuk.kim@softwareinlife.com> static resource를
     *         application에서 제공할 수 있도록 처리
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/web/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/pages/**").addResourceLocations("classpath:/templates/");

//
//
//        /* '/js/**'로 호출하는 자원은 '/static/js/' 폴더 아래에서 찾는다. */
//        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/").setCachePeriod(60 * 60 * 24 * 365);
//        /* '/css/**'로 호출하는 자원은 '/static/css/' 폴더 아래에서 찾는다. */
//        registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/").setCachePeriod(60 * 60 * 24 * 365);
//        /* '/img/**'로 호출하는 자원은 '/static/img/' 폴더 아래에서 찾는다. */
//        registry.addResourceHandler("/img/**").addResourceLocations("classpath:/static/img/").setCachePeriod(60 * 60 * 24 * 365);
//        /* '/font/**'로 호출하는 자원은 '/static/font/' 폴더 아래에서 찾는다. */
//        registry.addResourceHandler("/font/**").addResourceLocations("classpath:/static/font/").setCachePeriod(60 * 60 * 24 * 365);


    }

}
