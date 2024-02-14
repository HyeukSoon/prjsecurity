package com.hyeuksp.pjtsecurity.config;

import com.hyeuksp.pjtsecurity.interceptor.LoggerInterceptor;
import com.hyeuksp.pjtsecurity.interceptor.LoginCheckInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    private static final Logger logger = LoggerFactory.getLogger(MvcConfig.class);

    // 요청 - 뷰 연결
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        logger.info("registry --->"+registry);
        registry.addRedirectViewController("/", "/member/login");
      //  registry.addViewController("/").setViewName("main");
        registry.addViewController("/member/login").setViewName("/member/login");
        registry.addViewController("/member/admin").setViewName("/member/admin");
        registry.addViewController("/member/signup").setViewName("/member/signup");
        registry.addViewController("/member/join").setViewName("/member/join");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoggerInterceptor())
                .excludePathPatterns("/css/**", "/images/**", "/js/**");

        registry.addInterceptor(new LoginCheckInterceptor())
                .addPathPatterns("/**/*.do")
                .excludePathPatterns("/log*");
    }
}
