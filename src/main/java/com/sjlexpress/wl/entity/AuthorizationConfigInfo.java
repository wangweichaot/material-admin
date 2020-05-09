package com.sjlexpress.wl.entity;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: wangweichao
 * @date: 2020-4-2
 * @desc:角色管理入参实体
 */
@Data
@ApiModel(value = "com.sjlexpress.wl.vo.AuthorizationConfigInfo", description = "角色管理入参实体")
public class AuthorizationConfigInfo {
	
	@ApiModelProperty(value = "角色主键")
	@NotBlank
	private String id;
	
	@ApiModelProperty(value = "角色Id")
    private String ids;

	
}