package com.demo.springboot.modules.nav.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 收藏导航表
 * </p>
 *
 * @author macro
 * @since 2023-08-09
 */
@Getter
@Setter
@TableName("tb_nav")
@ApiModel(value = "TbNav对象", description = "收藏导航表")
public class TbNav implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("分类")
    private String sort;

    @ApiModelProperty("地址")
    private String url;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("封面")
    private String picture;

    @ApiModelProperty("平均")
    private String evaluate;

    @ApiModelProperty("简介")
    private String note;

    @ApiModelProperty("创建人")
    private String createUser;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("修改人")
    private String updateUser;

    private Date updateTime;


}
