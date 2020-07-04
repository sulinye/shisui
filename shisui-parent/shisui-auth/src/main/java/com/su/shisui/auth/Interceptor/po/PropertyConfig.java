package com.su.shisui.auth.Interceptor.po;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * author sly
 */
@Configuration
@ConfigurationProperties(prefix = "shisui")
@Data
public class PropertyConfig {

    private List<String> ignoreUrl;

    private List<String> userInfoIgnoreUrl;

}
