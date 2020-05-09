package com.sjlexpress.wl.vo;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;

import com.sjlexpress.wl.dto.SystemMenuDto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: wangweichao
 * @date: 2020-4-15
 * @desc:菜单管理入参实体
 */
@Data
@ApiModel(value = "com.sjlexpress.wl.vo.SystemMenuVo", description = "菜单管理入参实体")
public class SystemMenuVo {
	
	@ApiModelProperty(value = "菜单主键/菜单编号")
	private Integer id;
	
	@ApiModelProperty(value = "父级ID")
    private String pid;
	
	@ApiModelProperty(value = "是否父级 0否1是")
    private Integer isParent;

	@ApiModelProperty(value = "菜单英文名称")
    private String name;

	@ApiModelProperty(value = "菜单路径")
    private String path;

	@ApiModelProperty(value = "菜单中文名称")
    private String label;

	@ApiModelProperty(value = "是否展示 0否 1是")
    private Integer isShow;

	@ApiModelProperty(value = "排序编号")
    private Integer orders;

	@ApiModelProperty(value = "菜单小图标")
    private String icon;

	@ApiModelProperty(value = "是否有效 0启用 1禁用")
    private Integer isDelete;

	@ApiModelProperty(value = "创建人")
    private String createUser;

	@ApiModelProperty(value = "创建时间")
    private Date createTime;

	@ApiModelProperty(value = "更新人")
    private String updateUser;

	@ApiModelProperty(value = "更新时间")
    private Date updateTime;

	@ApiModelProperty(value = "编辑回显所需的id集合")
	private String ids;
	
	@ApiModelProperty(value = "菜单子级（仅登录渲染菜单用）")
    private List<SystemMenuVo> children;
    
}