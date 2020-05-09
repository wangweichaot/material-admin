package com.sjlexpress.wl.vo;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: wangweichao
 * @date: 2020-4-8
 * @desc:员工启用禁用入参实体
 */
@Data
@ApiModel(value = "com.sjlexpress.wl.vo.SystemUserUpdateVo", description = "员工启用禁用入参实体")
public class SystemUserUpdateVo {
	
	@ApiModelProperty(value = "员工主键", required = true)
	@NotBlank(message = "员工ID不能为空")
    private String id;
	 
	@ApiModelProperty(value = "操作标志 1启用 0禁用", required = true)
	@NotBlank(message = "操作标志不能为空")
    private String operFlag;

	
	 
    
}