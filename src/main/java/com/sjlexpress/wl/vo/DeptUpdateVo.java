package com.sjlexpress.wl.vo;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: wangweichao
 * @date: 2020-5-7
 * @desc:部门/仓库启用禁用入参实体
 */
@Data
@ApiModel(value = "com.sjlexpress.hyt.vo.DeptUpdateVo", description = "部门/仓库启用禁用入参实体")
public class DeptUpdateVo {
	
	@ApiModelProperty(value = "部门/仓库主键", required = true)
	@NotBlank(message = "部门/仓库ID不能为空")
    private Integer id;
	 
	@ApiModelProperty(value = "操作标志 1启用 0禁用", required = true)
	@NotBlank(message = "操作标志不能为空")
    private String operFlag;

	
	 
    
}