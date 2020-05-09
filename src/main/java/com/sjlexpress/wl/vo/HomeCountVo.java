package com.sjlexpress.wl.vo;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: wangweichao
 * @date: 2020-4-28
 * @desc:首页统计返回的实体
 */
@Data
@ApiModel(value = "com.sjlexpress.wl.vo.HomeCountVo", description = "首页统计返回的实体")
public class HomeCountVo {
	
	@ApiModelProperty(value = "今日入库")
    private Integer storageNumToday;

	@ApiModelProperty(value = "今日新增用户")
    private Integer newUserNumToday;

	@ApiModelProperty(value = "今日订单")
    private Integer orderNumToday;

	@ApiModelProperty(value = "今日交易额")
    private BigDecimal transNumToday;

	@ApiModelProperty(value = "本月入库")
    private Integer storageNumMonth;

	@ApiModelProperty(value = "本月新增用户")
    private Integer newUserNumMonth;

	@ApiModelProperty(value = "本月订单")
    private Integer orderNumMonth;

	@ApiModelProperty(value = "本月交易额")
    private BigDecimal transNumMonth;

    
}