package com.su.shisui.auth.common;

import com.su.shisui.auth.common.po.User;

import java.util.HashMap;
import java.util.Map;

/**
 * author sly
 */
public class TokenUtil<T> {

    public String createToken (T tokenObject) {
        //token 有效期2小时
        String token = WebTokenUtil.createJavaWebToken(createJwtClaims(tokenObject),
                60 * 60 * 24 * 30);
        return token;
    }

    /**
     * 封装JWT 荷载参数
     *
     * @param tokenObject 生成参数
     * @return Map key -  value
     */
    private Map<String, Object> createJwtClaims(T tokenObject) {
        Map<String, Object> claims = new HashMap<>(10);

        if (tokenObject instanceof User) {
            //荷载管理员用户参数
            User user = (User) tokenObject;
            claims.put("uId", user.getId());
//            claims.put("uAccount", user.getUserAccount());
//            claims.put("uRoleCode", user.getRoleCode());
        }
//        else if (tokenObject instanceof DoctorUser) {
//            //荷载医生用户参数
//            DoctorUser doctorUser = (DoctorUser) tokenObject;
//            claims.put("uCode", doctorUser.getCode());
//            claims.put("uAccount", doctorUser.getPhone());
//            claims.put("uRoleCode", doctorUser.getRoleCode());
//        } else if (tokenObject instanceof AppUser) {
//            //荷载普通用户参数
//            AppUser appUser = (AppUser) tokenObject;
//            claims.put("uCode", appUser.getCode());
//            claims.put("uAccount", appUser.getPhone());
//        }
        return claims;
    }
}
