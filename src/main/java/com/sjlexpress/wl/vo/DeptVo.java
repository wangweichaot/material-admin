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
 * @date: 2020-5-7
 * @desc:部门/仓库入参实体
 */
@Data
@ApiModel(value = "com.sjlexpress.wl.vo.DeptVo", description = "部门/仓库入参实体")
public class DeptVo {
	
	@ApiModelProperty(value = "主键/部门/仓库编码")
    private Integer id;

	@ApiModelProperty(value = "部门/仓库名称")
    private String name;

	@ApiModelProperty(value = "所属省ID")
    private String provinceId;

	@ApiModelProperty(value = "所属市ID")
    private String cityId;

	@ApiModelProperty(value = "所属区ID")
    private String areaId;

	@ApiModelProperty(value = "所属省名称")
    private String provinceName;

	@ApiModelProperty(value = "所属市名称")
    private String cityName;

	@ApiModelProperty(value = "所属区名称")
    private String areaName;

	@ApiModelProperty(value = "详细地址")
    private String address;

	@ApiModelProperty(value = "联系电话（一般是座机）")
    private String phone;

	@ApiModelProperty(value = "是否有效 0启用 1禁用")
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