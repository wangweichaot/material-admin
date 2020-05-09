package com.sjlexpress.wl.vo;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: wangweichao
 * @date: 2020-4-8
 * @desc:员工管理入参实体
 */
@Data
@ApiModel(value = "com.sjlexpress.wl.vo.SystemUserVo", description = "员工管理入参实体")
public class SystemUserVo {
	
	@ApiModelProperty(value = "主键")
    private String id;

	@ApiModelProperty(value = "登录账户名称")
    private String userAccount;

	@ApiModelProperty(value = "登录密码")
    private String password;

	@ApiModelProperty(value = "用户名称")
    private String userName;

	@ApiModelProperty(value = "性别 1男 2女")
    private String sex;

	@ApiModelProperty(value = "是否有效 0启用 1禁用")
    private Integer isDelete;
	
	@ApiModelProperty(value = "角色ID")
    private Integer roleId;
	
	@ApiModelProperty(value = "角色名称")
    private String roleName;

    
}