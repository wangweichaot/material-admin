package com.sjlexpress.wl.service;

import com.sjlexpress.wl.exception.AlertException;
import com.sjlexpress.wl.vo.HomeCountVo;

/**
 * 首页接口
 * @author wangweichao
 */
public interface IHomeService {

    /**
     * 首页统计接口
     * @param tokenVo
     * @param id
     * @return
     * @throws AlertException
     */
    HomeCountVo getHomeCountInfo() throws AlertException;

}
