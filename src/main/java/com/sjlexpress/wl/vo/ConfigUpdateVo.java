package com.sjlexpress.wl.vo;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: wangweichao
 * @date: 2020-5-8
 * @desc:公共配置启用禁用入参实体
 */
@Data
@ApiModel(value = "com.sjlexpress.wl.vo.ConfigUpdateVo", description = "公共配置启用禁用入参实体")
public class ConfigUpdateVo {
	
	@ApiModelProperty(value = "公共配置主键", required = true)
	@NotBlank(message = "公共配置ID不能为空")
    private String id;
	 
	@ApiModelProperty(value = "操作标志 1启用 0禁用", required = true)
	@NotBlank(message = "操作标志不能为空")
    private String operFlag;

	
	 
    
}