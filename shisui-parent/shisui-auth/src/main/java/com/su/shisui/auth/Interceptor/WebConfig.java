package com.su.shisui.auth.Interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * author sly
 */
@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

// 第一种注入
    @Autowired
    UserInterceptor userInterceptor;
// 第二种注入
//    @Bean
//    public UserInterceptor userInterceptor(){//        return new UserInterceptor();
//    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userInterceptor)
//                .addPathPatterns("/V1/auth/**");
                .addPathPatterns("/**");
//                .excludePathPatterns("/V1/order/**");
        super.addInterceptors(registry);
    }

//    new UserInterceptor()错误 拦截器中取不到yml值 切记只能注入不能new
//    @Override
//    protected void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new UserInterceptor())
//                .addPathPatterns("/**");
//        super.addInterceptors(registry);
//    }
}
