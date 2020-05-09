package com.sjlexpress.wl.param;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author wangweichao
 */
@ApiModel(value = "com.sjlexpress.wl.param.ConfigParam", description = "公共配置管理查询入参对象")
@Data
public class ConfigParam extends PageParam implements Serializable{
	
	private static final long serialVersionUID = 6068901290463872616L;
	
    @ApiModelProperty(value = "名称")
    private String name;

}
