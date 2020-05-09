package com.sjlexpress.wl.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SystemMenuDto implements Serializable{
	
	private static final long serialVersionUID = 7009036752378804582L;

	/**
     * 主键/菜单编号
     */
    private String id;

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
     * 删除标志 1正常 0删除
     */
    private Integer flag;

    /**
     * 是否有效 0启用 1禁用
     */
    private Integer isDelete;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新人
     */
    private String updateUser;

    /**
     * 更新时间
     */
    private Date updateTime;
    
    /**
     * 编辑回显所需的id集合
     */
	private String ids;
	
	/**
     * 子节点
     */
    private List<SystemMenuDto> children;

    

}