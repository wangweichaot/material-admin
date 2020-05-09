package com.sjlexpress.wl.vo;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: wangweichao
 * @date: 2020-4-24
 * @desc:角色管理入参实体
 */
@Data
@ApiModel(value = "com.sjlexpress.wl.vo.SystemRoleVo", description = "角色管理入参实体")
public class SystemRoleVo {
	
	@ApiModelProperty(value = "角色主键")
	private Integer id;
	
//	@ApiModelProperty(value = "角色Id")
//    private String roleId;

	@ApiModelProperty(value = "角色名称")
    private String roleName;
	
	@ApiModelProperty(value = "权限配置回显ID集合")
	private String ids;
	
	@ApiModelProperty(value = "是否超级管理员 0否 1是")
    private Integer isSuper;
    
	@ApiModelProperty(value = "是否有效 0启用 1禁用")
    private Integer isDelete;

	@ApiModelProperty(value = "创建人")
    private String createUser;

	@ApiModelProperty(value = "创建时间")
    private Date createTime;

	@ApiModelProperty(value = "更新人")
    private String updateUser;

	@ApiModelProperty(value = "更新时间")
    private Date updateTime;
    
}