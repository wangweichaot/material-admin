package com.sjlexpress.wl.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;

import lombok.Data;

@Data
public class ConfigDto implements Serializable{
	
	private static final long serialVersionUID = 7009036752378804582L;

	/**
     * ID主键
     */
    private String id;

    /**
     * 配置文件名
     */
    private String name;
    
    /**
     * 配置文件的值
     */
    private String value;

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
    private Integer isDelete;

    /**
     * 添加用户ID
     */
    private String createUser;

    /**
     * 添加时间
     */
    private Date createTime;

    /**
     * 修改用户ID
     */
    private String updateUser;

    /**
     * 修改时间
     */
    private Date updateTime;

    
}