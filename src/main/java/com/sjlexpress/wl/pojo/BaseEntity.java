package com.sjlexpress.wl.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Date;

/**
 * Entity基类 数据具有增改信息
 *
 * @author zhicheng.lai
 */
@ApiModel(value = "com.sjlexpress.wl.pojo.BaseEntity", description = "添加与修改数据信息")
@Data
public class BaseEntity implements Serializable {

//    /**
//     * 添加用户ID
//     */
//    @ApiModelProperty(value = "添加用户ID")
//    @Column(name = "create_user")
//    private String createUser;
//
//    /**
//     * 添加时间
//     */
//    @ApiModelProperty(value = "添加时间")
//    @Column(name = "create_time")
//    private Date createTime;
//
//    /**
//     * 修改用户ID
//     */
//    @ApiModelProperty(value = "修改用户ID")
//    @Column(name = "update_user")
//    private String updateUser;
//
//    /**
//     * 修改时间
//     */
//    @ApiModelProperty(value = "修改时间")
//    @Column(name = "update_time")
//    private Date updateTime;



}
