package com.sjlexpress.wl.vo;


import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 
 * @author: linzhenhui
 * @date: 2020年3月29日下午8:53:58
 * @desc:
 */
@Data
@ApiModel(value = "com.sjlexpress.wl.vo.SysUserVo", description = "用户信息")
public class SysUserVo {

	 @ApiModelProperty(value = "用户的token", hidden = true)
	 private String token;
	 
	 @ApiModelProperty(value = "用户的菜单", hidden = true)
	 private List<SystemMenuVo> menuList;
	 
	 @ApiModelProperty(value = "用户ID", hidden = true)
	 private String userId;
	 
	 @ApiModelProperty(value = "登录账户名称", hidden = true)
	 private String userAccount;
	 
	 @ApiModelProperty(value = "用户名称", hidden = true)
	 private String userName;
	 

}