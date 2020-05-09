package com.sjlexpress.wl.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Table(name = "system_role_menu")
@Data
public class SystemRoleMenu extends BaseEntity {
    /**
     * 主键
     */
    @Id
    private String id;

    /**
     * 角色ID
     */
    @Column(name = "role_id")
    private Integer roleId;

    /**
     * 菜单ID
     */
    @Column(name = "menu_id")
    private String menuId;
    
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

    
}