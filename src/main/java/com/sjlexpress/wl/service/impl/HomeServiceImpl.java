package com.sjlexpress.wl.service.impl;


import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sjlexpress.wl.exception.AlertException;
import com.sjlexpress.wl.service.IHomeService;
import com.sjlexpress.wl.vo.HomeCountVo;

/**
 * 功能描述: 首页接口实现
 *
 * @author wangweichao
 */
@Service
public class HomeServiceImpl implements IHomeService {

    /**
	 * 首页统计接口
	 */
	@Override
	public HomeCountVo getHomeCountInfo() throws AlertException {
		HomeCountVo homeCountVo = new HomeCountVo();
		
//		//1、今日入库
//		int storageNumToday = parcelMapper.getStorageNumToday();
//		homeCountVo.setStorageNumToday(storageNumToday);
//		
//		//2、今日新增用户
//		int newUserNumToday = userMapper.getNewUserNumToday();
//		homeCountVo.setNewUserNumToday(newUserNumToday);
//		
//		//3、今日订单
//		int orderNumToday = orderMapper.getOrderNumToday();
//		homeCountVo.setOrderNumToday(orderNumToday);
//		
//		//4、今日交易额
//		String transNumToday = orderPayMapper.getTransNumToday();
//		if(StringUtilsWL.isEmpty(transNumToday)) {
//			homeCountVo.setTransNumToday(new BigDecimal(0.00));
//		}else {
//			BigDecimal transNumToday_ = new BigDecimal(transNumToday).setScale(2, BigDecimal.ROUND_HALF_UP);
//			homeCountVo.setTransNumToday(transNumToday_);
//		}
//		
//		//5、本月入库
//		int storageNumMonth = parcelMapper.getStorageNumToday();
//		homeCountVo.setStorageNumMonth(storageNumMonth);
//		
//		//6、本月新增用户
//		int newUserNumMonth = userMapper.getNewUserNumMonth();
//		homeCountVo.setNewUserNumMonth(newUserNumMonth);
//		
//		//7、本月订单
//		int orderNumMonth = orderMapper.getOrderNumMonth();
//		homeCountVo.setOrderNumMonth(orderNumMonth);
//		
//		//8、本月交易额
//		String transNumMonth = orderPayMapper.getTransNumMonth();
//		if(StringUtilsWL.isEmpty(transNumMonth)) {
//			homeCountVo.setTransNumMonth(new BigDecimal(0.00));
//		}else {
//			BigDecimal transNumMonth_ = new BigDecimal(transNumMonth).setScale(2, BigDecimal.ROUND_HALF_UP);
//			homeCountVo.setTransNumMonth(transNumMonth_);
//		}
		
		return homeCountVo;
	}


}
