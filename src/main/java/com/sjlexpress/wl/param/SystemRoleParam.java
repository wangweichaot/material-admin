package com.sjlexpress.wl.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author wangweichao
 */
@ApiModel(value = "com.sjlexpress.wl.param.SystemRoleParam", description = "角色管理入参对象")
@Data
public class SystemRoleParam extends PageParam {
	
    @ApiModelProperty(name = "角色名称", value = "角色名称", required = true)
    private String roleName;
    
}
