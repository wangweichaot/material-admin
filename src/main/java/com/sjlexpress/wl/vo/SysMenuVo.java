package com.sjlexpress.wl.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 
 * @author: linzhenhui
 * @date: 2020年3月29日下午8:53:46
 * @desc:
 */
@Data
@ApiModel(value = "com.sjlexpress.wl.vo.SysMenuVo", description = "菜单")
public class SysMenuVo {
	 
	 @ApiModelProperty(value = "menuId")
	    private String menuId;

	 @ApiModelProperty(value = "menuName")
	    private String menuName;

}