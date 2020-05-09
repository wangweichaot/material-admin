package com.sjlexpress.wl.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Table(name = "system_role")
@Data
public class SystemRole extends BaseEntity {

	private static final long serialVersionUID = -8985484126772786561L;

	/**
     * 主键
     */
    @Id
    private Integer id;

//    /**
//     * 角色Id
//     */
//    @Column(name = "role_id")
//    private String roleId;

    /**
     * 角色名称
     */
    @Column(name = "role_name")
    private String roleName;
    
    /**
     * 权限配置回显ID集合
     */
    @Column(name = "ids")
	private String ids;
    
    /**
     * 是否超级管理员 0否 1是
     */
    @Column(name = "is_super")
    private Integer isSuper;
    
    /**
     * 是否有效 0启用 1禁用
     */
    @Column(name = "is_delete")
    private Integer isDelete;

    /**
     * 创建人
     */
    @Column(name = "create_user")
    private String createUser;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新人
     */
    @Column(name = "update_user")
    private String updateUser;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;
    
}