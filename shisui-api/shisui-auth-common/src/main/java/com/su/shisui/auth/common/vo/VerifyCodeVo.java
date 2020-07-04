package com.su.shisui.auth.common.vo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * author sly
 */
@Data
@ApiModel("验证码VO")
@AllArgsConstructor // 生成全参数构造函数
@NoArgsConstructor // 生成无参构造函数
@Builder // 可以直接通过builder设置字段参数
public class VerifyCodeVo {

    private String uuid;

    private String base64Image;
}
