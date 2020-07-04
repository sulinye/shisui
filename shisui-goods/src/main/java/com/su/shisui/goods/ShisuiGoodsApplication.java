package com.su.shisui.goods;

import com.netflix.discovery.converters.Auto;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@ComponentScan("com.su.shisui")
@EnableEurekaClient
@SpringBootApplication
//@MapperScan("com.su.shisui.goods.mapper")
public class ShisuiGoodsApplication {

    @Autowired
    private Environment env;

    public static void main(String[] args) {
        SpringApplication.run(ShisuiGoodsApplication.class, args);
    }
//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurerAdapter() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/goods/findGoodsList/*").allowedOrigins("http://localhost:61002");
//            }
//        };
//    }
    @Bean
    @ConditionalOnExpression()
    public FilterRegistrationBean corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        String originsStr = env.getProperty("shisui.allowedOrigins");
        String[] allowedOrigins = null;
        if (null != originsStr && !StringUtils.isEmpty(originsStr.trim())) {
            allowedOrigins = originsStr.split(";");
            for (String origin : allowedOrigins) {
                config.addAllowedOrigin(origin);
            }
        } else {
            config.addAllowedOrigin("*");
        }
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        config.setMaxAge(86400L);
        source.registerCorsConfiguration("/**",config);
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(0);
        return bean;
    }

}
