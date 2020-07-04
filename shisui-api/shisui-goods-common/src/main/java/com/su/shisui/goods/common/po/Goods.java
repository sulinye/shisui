package com.su.shisui.goods.common.po;

import lombok.Data;

import java.util.Date;

/**
 * author sly
 */
@Data
public class Goods {

    private Long id;

    private String name;

    private Long createBy;

    private Date createTime;

    private String createIp;

    private Long modifyBy;

    private Date modifyTime;

    private String modifyIp;

    private Integer isDeleted;
}
