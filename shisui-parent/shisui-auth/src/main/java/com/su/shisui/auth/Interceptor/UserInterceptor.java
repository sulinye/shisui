package com.su.shisui.auth.Interceptor;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.su.shisui.api.auth.service.UserService;
import com.su.shisui.auth.Interceptor.po.PropertyConfig;
import com.su.shisui.auth.Interceptor.utils.ServletUrlUtil;
import com.su.shisui.auth.common.WebTokenUtil;
import com.su.shisui.auth.common.po.User;
import com.su.shisui.common.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.stylesheets.LinkStyle;
import springfox.documentation.spring.web.json.Json;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

/**
 * author sly
 */
@Component(value = "login")
@Slf4j
public class UserInterceptor implements HandlerInterceptor {

    @Value("shisui.test")
    String test;

    @Autowired
    PropertyConfig propertyConfig;

    @Autowired
    ServletUrlUtil servletUrlUtil;

    @Autowired
    UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.debug("token",request.getHeader("token"));
        System.out.println("token："+request.getHeader("token"));
        System.out.println("reqUrl："+request.getServletPath());
        String token = request.getHeader("token");
        String reqUrl = request.getServletPath();
        List ignoreUrlList = propertyConfig.getIgnoreUrl();
        boolean isIgnoreUrl = propertyConfig.getIgnoreUrl().stream().anyMatch(ignoreUrl -> servletUrlUtil.servletPathMatch(ignoreUrl,reqUrl));
//        忽略的url 不拦截 游客 或登录 获取验证码
        if (isIgnoreUrl) {
            System.out.println("校验为不需要验证的链接直接通过："+reqUrl+"-"+ isIgnoreUrl);
            return true;
        }

        if (!StringUtils.isEmpty(token) && !"null".equals(token)) {
            Map<String, Object> resolveMap = WebTokenUtil.parserJavaWebToken(token);
            String id = (String) resolveMap.get("id");
            System.out.println("id:"+id);
            User user = userService.findUserById(Long.parseLong(id));
            Map userMap = new ObjectMapper().readValue(JSON.toJSONString(user),Map.class);
            UserInfoContextHolder.getInstance().setContext(userMap);
            return true;
        }
        returnJson(response, JSON.toJSONString(Result.fail("没有登录！")));
        return false;
    }



    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserInfoContextHolder.getInstance().clear();
    }

    private void returnJson(HttpServletResponse response, String json) {
        PrintWriter writer = null;
        try{
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            response.getWriter().print(json);
        }catch (IOException e){
            log.error("response error,{}",e);
        }finally{
            if (writer != null) {
                writer.close();
            }
        }
    }
}
