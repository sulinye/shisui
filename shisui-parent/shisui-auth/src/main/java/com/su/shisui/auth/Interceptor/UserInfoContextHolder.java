package com.su.shisui.auth.Interceptor;

import java.util.Map;

/**
 * author sly
 */
public class UserInfoContextHolder {

    private ThreadLocal<Map<String,String>> threadLocal;

    private UserInfoContextHolder() {this.threadLocal = new ThreadLocal<>();}

    public static UserInfoContextHolder getInstance() {return SingletonHolder.sInstance;}

    private static class SingletonHolder {
        private static final UserInfoContextHolder sInstance = new UserInfoContextHolder();
    }

    public void setContext(Map<String,String> map) {threadLocal.set(map);}

    public Map<String, String> getContext() {return threadLocal.get();}

    public void clear() {threadLocal.remove();}
}
