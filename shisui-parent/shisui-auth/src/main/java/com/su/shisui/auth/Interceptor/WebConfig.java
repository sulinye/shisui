package com.su.shisui.auth.Interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * author sly
 */
@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserInterceptor())
//                .addPathPatterns("/V1/auth/**");
                .addPathPatterns("/**");
//                .excludePathPatterns("/V1/order/**");
        super.addInterceptors(registry);
    }
}
