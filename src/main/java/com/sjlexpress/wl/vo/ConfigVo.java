package com.sjlexpress.wl.vo;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: wangweichao
 * @date: 2020-5-8
 * @desc:公共配置管理入参实体
 */
@Data
@ApiModel(value = "com.sjlexpress.wl.vo.ConfigVo", description = "公共配置管理入参实体")
public class ConfigVo {
	
	@ApiModelProperty(value = "公共配置主键")
	private String id;

	@ApiModelProperty(value = "名称")
	@NotBlank
    private String name;
	
	@ApiModelProperty(value = "值")
	@NotBlank
    private String value;

	@ApiModelProperty(value = "类型: int,double,img,string,radio,editor,text,time,list,long,json")
    private String type;

	@ApiModelProperty(value = "描述")
    private String description;

	@ApiModelProperty(value = "排序或分组")
    private Integer sort;

	@ApiModelProperty(value = "有效标志 0启用 1禁用")
    private Integer isDelete;

	@ApiModelProperty(value = "创建人ID")
    private String createUser;

	@ApiModelProperty(value = "创建时间")
    private Date createTime;

	@ApiModelProperty(value = "修改人ID")
    private String updateUser;

	@ApiModelProperty(value = "修改时间")
    private Date updateTime;

}