package com.zzxy.pj.common.config;

import com.zzxy.pj.common.web.interceptor.TimeHandlerIntercptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration//配置类注解
public class SpringWebConfig implements WebMvcConfigurer {
    //添加拦截路径的方法
    public void addInterceptors(InterceptorRegistry registry) {
        //添加拦截器
        InterceptorRegistration interceptor = registry.addInterceptor(new TimeHandlerIntercptor());
        //添加拦截器路径
        interceptor.addPathPatterns("/user/doLogin");
        interceptor.addPathPatterns("/log/log_list");
    }
}
