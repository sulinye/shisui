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

    private List<String> colorOs;

    public List<String> getIgnoreUrl() {
        return ignoreUrl;
    }

    public void setIgnoreUrl(List<String> ignoreUrl) {
        this.ignoreUrl = ignoreUrl;
    }

    public List<String> getUserInfoIgnoreUrl() {
        return userInfoIgnoreUrl;
    }

    public void setUserInfoIgnoreUrl(List<String> userInfoIgnoreUrl) {
        this.userInfoIgnoreUrl = userInfoIgnoreUrl;
    }

    public List<String> getColorOs() {
        return colorOs;
    }

    public void setColorOs(List<String> colorOs) {
        this.colorOs = colorOs;
    }
}
