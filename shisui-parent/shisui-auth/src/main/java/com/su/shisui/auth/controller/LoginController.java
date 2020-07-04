package com.su.shisui.auth.controller;

import com.su.shisui.api.auth.service.UserService;
import com.su.shisui.auth.common.TokenUtil;
import com.su.shisui.auth.common.bo.UserBo;
import com.su.shisui.auth.common.po.ImageCode;
import com.su.shisui.auth.common.po.User;
import com.su.shisui.auth.common.vo.UserVo;
import com.su.shisui.auth.common.vo.VerifyCodeVo;
import com.su.shisui.auth.common.vo.VerifyCodeVo.VerifyCodeVoBuilder;
import com.su.shisui.common.cache.Constant;
import com.su.shisui.common.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static com.su.shisui.common.WebServerVersion.AUTH_WEB_V;

/**
 * author sly
 */
@RestController
@Api(tags = "用户认证")
@RequestMapping(AUTH_WEB_V+"/auth")
public class LoginController {

    private TokenUtil<User> tokenUtil = new TokenUtil<>();
    private static String VERIFY_CODE = "";
    private static String UID = "";

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping("login")
    @ApiOperation("用户登录接口")
    public Result<UserVo> login(@RequestBody UserBo userBo){
//    public Result<UserVo> login(){
//        UserBo userBo = new UserBo();
//        userBo.setUserName("sly");
//        userBo.setPassword("123");
        Result result = checkLogin(userBo);
        if (null != result) {
            return result;
        }
        User user = new User();
        user.setUserName(userBo.getUserName());
        User userNew = userService.login(user);
        UserVo userVo = new UserVo();
        if (null == userNew) {
            return Result.fail("用户不存在！");
        } else if (!userBo.getPassword().equals(userNew.getPassword())) {
            return Result.fail("密码错误！");
        } else if (!userBo.getUuid().equals(UID) || !userBo.getVerifyCode().equals(VERIFY_CODE)) {
            return Result.fail("验证码错误！");
        } else {
//            认证通过，创建token
            String token = tokenUtil.createToken(userNew);
//            BeanUtils.copyProperties(userNew,userVo);
            userVo.setToken(token);
//            redisTemplate.opsForValue().set(String.format(Constant.REDIS_KEY_APP_TOKEN,userNew.getId()),userVo,30L, TimeUnit.DAYS);
        }
        return Result.ok(userVo);
    }

    private Result checkLogin(UserBo userBo) {
        if (null == userBo) {
            return Result.fail("不能为空");
        }
        if (StringUtils.isEmpty(userBo.getUserName())) {
            return Result.fail("用户名不能为空");
        }
        if (StringUtils.isEmpty(userBo.getPassword())) {
            return Result.fail("密码不能为空");
        }
        return null;
    }

    @GetMapping("/getImageData")
    @ApiOperation("获取验证码")
    public Result<VerifyCodeVo> getImageData(){
//        VerifyCodeVo verifyCodeVo = new VerifyCodeVoBuilder().uuid().build();
        VerifyCodeVo verifyCodeVo = new VerifyCodeVo();
        try {
            final ImageCode imageCode = this.userService.generateCode();
            final String uuid = UUID.randomUUID().toString();
//            this.redisTemplate.opsForValue().set(uuid, imageCode.getSRand(), 120, TimeUnit.SECONDS);
            VERIFY_CODE = imageCode.getSRand();
            UID = uuid;
            verifyCodeVo.setUuid(uuid);
            verifyCodeVo.setBase64Image(imageCode.getBase64Image());
        }catch (final IOException e) {
            System.out.println(e);
        }
        return Result.ok(verifyCodeVo);
    }
}
