package com.sjlexpress.wl.param;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author wangweichao
 */
@ApiModel(value = "com.sjlexpress.wl.param.SystemUserParam", description = "员工管理查询入参对象")
@Data
public class SystemUserParam extends PageParam implements Serializable{
	
	private static final long serialVersionUID = -66218217631161983L;

	@ApiModelProperty(value = "登录账户")
    private String userAccount;
	
	@ApiModelProperty(value = "用户名称")
    private String userName;

    
}
