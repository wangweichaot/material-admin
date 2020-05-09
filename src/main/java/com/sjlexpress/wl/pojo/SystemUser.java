package com.sjlexpress.wl.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Table(name = "system_user")
@Data
public class SystemUser extends BaseEntity {

	private static final long serialVersionUID = -3895928817397703851L;

	/**
     * 主键
     */
    @Id
    private String id;

    /**
     * 登录账户名称
     */
    @Column(name = "user_account")
    private String userAccount;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 用户名称
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 性别 1男 2女
     */
    private String sex;

    /**
     * 创建人ID
     */
    @Column(name = "create_user")
    private String createUser;


    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新人ID
     */
    @Column(name = "update_user")
    private String updateUser;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 是否有效 0启用 1禁用
     */
    @Column(name = "is_delete")
    private Integer isDelete;

    
}