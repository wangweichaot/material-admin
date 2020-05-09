package com.sjlexpress.wl.vo;


import java.util.List;


import com.sjlexpress.wl.pojo.MenuInfo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 
 * @author: linzhenhui
 * @date: 2020年3月29日下午8:53:52
 * @desc:
 */
@Data
@ApiModel(value = "com.sjlexpress.wl.vo.SysRoleVo", description = "角色信息")
public class SysRoleVo {
	 
	 @ApiModelProperty(value = "roleId")
	 private String roleId;

	 @ApiModelProperty(value = "roleName")
	 private String roleName;
	 
	 //角色状态 1启动 0禁用
	 @ApiModelProperty(value = "roleStatus")
	 private String roleStatus;
	 
	 @ApiModelProperty(value = "角色授权时的菜单信息")
	 private List<MenuInfo> menuInfoList;
	 
}