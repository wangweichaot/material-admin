package com.sjlexpress.wl.service;

import com.sjlexpress.wl.dto.ConfigDto;
import com.sjlexpress.wl.exception.AlertException;
import com.sjlexpress.wl.param.ConfigParam;
import com.sjlexpress.wl.vo.ConfigUpdateVo;
import com.sjlexpress.wl.vo.ConfigVo;
import com.sjlexpress.wl.vo.PageVo;
import com.sjlexpress.wl.vo.TokenVo;

/**
 * 公共配置管理接口层
 */
public interface IConfigService {

	/**
	 * 分页查询公共配置列表
	 * @param tokenVo
	 * @param param
	 * @return
	 * @throws AlertException
	 */
    PageVo<ConfigDto> getConfigList(TokenVo tokenVo, ConfigParam param) throws AlertException;
    
    /**
     * 新增
     * @param addressBody
     * @param tokenVo
     * @throws AlertException
     */
    void addConfig(ConfigVo sysRoleVo, TokenVo tokenVo) throws AlertException;
    
    /**
     * 修改
     * @param addressBody
     * @param tokenVo
     * @throws AlertException
     */
    void updateConfig(ConfigVo sysRoleVo, TokenVo tokenVo) throws AlertException;
    
    /**
     * 详情
     * @param tokenVo
     * @param id
     * @return
     * @throws AlertException
     */
    ConfigVo getConfigDetail(TokenVo tokenVo, String id) throws AlertException;
    
    /**
     * 启用/禁用
     * @param addressBody
     * @param tokenVo
     * @throws AlertException
     */
    void openCloseConfig(ConfigUpdateVo sysRoleVo, TokenVo tokenVo) throws AlertException;
    

}
