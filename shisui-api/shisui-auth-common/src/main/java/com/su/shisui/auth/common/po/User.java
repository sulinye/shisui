package com.su.shisui.auth.common.po;

import lombok.Data;

import java.util.Date;

/**
 * author sly
 */
@Data
public class User {

    private String id;

    private String userName;

    private String password;

    private Long createBy;

    private Date createTime;

    private String createIp;

    private Long modifyBy;

    private Date modifyTime;

    private String modifyIp;

    private Integer isDeleted;

}
