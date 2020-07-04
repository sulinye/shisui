package com.su.shisui.goods.common.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * author sly
 */
@Data
@ApiModel(value = "商品BO")
public class GoodsBo {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "商品名称")
    private String name;

    @ApiModelProperty(value = "创建人")
    private Long createBy;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "创建ip")
    private String createIp;

    @ApiModelProperty(value = "修改人")
    private Long modifyBy;

    @ApiModelProperty(value = "修改时间")
    private Date modifyTime;

    @ApiModelProperty(value = "修改ip")
    private String modifyIp;

    @ApiModelProperty(value = "是否删除:1-是;0-否")
    private Integer isDeleted;
}
