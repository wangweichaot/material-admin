package com.sjlexpress.wl.param;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author wangweichao
 */
@ApiModel(value = "com.sjlexpress.wl.param.DeptParam", description = "部门/仓库入参对象")
@Data
public class DeptParam extends PageParam implements Serializable{
	
	private static final long serialVersionUID = 6068901290463872616L;
	
	@ApiModelProperty(value = "省ID")
    private String provinceId;
	
	@ApiModelProperty(value = "市ID")
    private String cityId;
	
	@ApiModelProperty(value = "区ID")
    private String areaId;
	
	@ApiModelProperty(value = "部门/仓库名称")
	private String name;
    
}
