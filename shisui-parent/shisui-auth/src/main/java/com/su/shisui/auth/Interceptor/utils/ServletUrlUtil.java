package com.su.shisui.auth.Interceptor.utils;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;


/**
 * author sly
 */
@Component
public class ServletUrlUtil {

    public boolean servletPathMatch(String userInfoUrl, String reqUrl){
        if (userInfoUrl.contains("*") || reqUrl.contains("**")) {
            userInfoUrl = StringUtils.remove(userInfoUrl,"*");
        }
        return reqUrl.startsWith(StringUtils.trim(userInfoUrl));
    }
}
