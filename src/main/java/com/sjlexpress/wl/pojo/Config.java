package com.sjlexpress.wl.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Table(name = "t_config")
@Data
public class Config extends BaseEntity {
    /**
     * ID主键
     */
    @Id
    private String id;

    /**
     * 配置文件名
     */
    private String name;

    /**
     * 配置文件类型: int,double,img,string,radio,editor,text,time,list,long,json
     */
    private String type;

    /**
     * 值的描述
     */
    private String description;

    /**
     * 排序或分组
     */
    private Integer sort;

    /**
     * 是否删除
     */
    @Column(name = "is_delete")
    private Integer isDelete;

    /**
     * 添加用户ID
     */
    @Column(name = "create_user")
    private String createUser;

    /**
     * 添加时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 修改用户ID
     */
    @Column(name = "update_user")
    private String updateUser;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 配置文件的值
     */
    private String value;

    
}