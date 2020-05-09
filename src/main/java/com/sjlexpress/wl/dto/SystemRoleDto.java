package com.sjlexpress.wl.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;

import lombok.Data;

@Data
public class SystemRoleDto implements Serializable{
	
	private static final long serialVersionUID = 7009036752378804582L;

	/**
     * 主键/角色Id
     */
    private String id;

//    /**
//     * 
//     */
//    private String roleId;

    /**
     * 角色名称
     */
    private String roleName;
    
    /**
     * 编辑回显所需的id集合
     */
	private String ids;
    
    /**
     * 是否有效 0启用 1禁用
     */
    private Integer isDelete;
    
    /**
     * 是否超级管理员 0否 1是
     */
    private Integer isSuper;
    
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