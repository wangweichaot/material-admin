package com.sjlexpress.wl.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Table(name = "system_menu")
@Data
public class SystemMenu extends BaseEntity {
    /**
     * 主键/菜单编号
     */
    @Id
    private Integer id;

    /**
     * 父级ID
     */
    private String pid;
    
    /**
     * 是否父级 0否1是
     */
    private Integer isParent;

    /**
     * 菜单英文名称
     */
    private String name;

    /**
     * 菜单路径
     */
    private String path;

    /**
     * 菜单中文名称
     */
    private String label;

    /**
     * 是否展示 0否 1是
     */
    @Column(name = "is_show")
    private Integer isShow;

    /**
     * 排序编号
     */
    private Integer orders;

    /**
     * 菜单小图标
     */
    private String icon;

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
    
    /**
     * 编辑回显所需的id集合
     */
    @Column(name = "ids")
	private String ids;

}