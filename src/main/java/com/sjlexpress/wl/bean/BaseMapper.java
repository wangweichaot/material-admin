package com.sjlexpress.wl.bean;


import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;


/**
 * 通用mapper父类
 *
 * @author zhicheng.lai
 */
public interface BaseMapper<T> extends MySqlMapper<T>, Mapper<T> {

}
