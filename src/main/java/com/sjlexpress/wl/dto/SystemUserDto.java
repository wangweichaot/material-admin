package com.sjlexpress.wl.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;

import lombok.Data;

@Data
public class SystemUserDto implements Serializable{

	private static final long serialVersionUID = -2976617817629998154L;

	/**
     * 主键
     */
    private String id;

    /**
     * 登录账户名称
     */
    private String userAccount;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 性别 1男 2女
     */
    private String sex;

    /**
     * 创建人ID
     */
    private String createId;

    /**
     * 创建人名称
     */
    private String createUser;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新人ID
     */
    private String updateUser;


    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 是否有效 0启用 1禁用
     */
    private Integer isDelete;
    
    /**
     * 角色ID
     */
    private Integer roleId;
    
    /**
     * 角色名称
     */
    private String roleName;
    
}


