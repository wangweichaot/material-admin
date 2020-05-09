package com.sjlexpress.wl.param;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author wangweichao
 */
@ApiModel(value = "com.sjlexpress.wl.param.SystemMenuParam", description = "菜单管理查询入参对象")
@Data
public class SystemMenuParam extends PageParam implements Serializable{
	
	private static final long serialVersionUID = 6068901290463872616L;
	
	@ApiModelProperty(value = "菜单中文名称")
    private String label;
	
    
}
