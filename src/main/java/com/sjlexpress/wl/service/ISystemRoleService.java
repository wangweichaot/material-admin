package com.sjlexpress.wl.service;

import java.util.List;

import com.sjlexpress.wl.dto.SystemRoleDto;
import com.sjlexpress.wl.entity.AuthorizationConfigInfo;
import com.sjlexpress.wl.exception.AlertException;
import com.sjlexpress.wl.param.SystemRoleParam;
import com.sjlexpress.wl.vo.PageVo;
import com.sjlexpress.wl.vo.SystemRoleVo;
import com.sjlexpress.wl.vo.TokenVo;

/**
 * 角色管理接口层
 */
public interface ISystemRoleService {

	/**
	 * 分页查询角色列表
	 * @param tokenVo
	 * @param param
	 * @return
	 * @throws AlertException
	 */
    PageVo<SystemRoleDto> getSystemRoleList(TokenVo tokenVo, SystemRoleParam param) throws AlertException;
    
    /**
	 * 查询角色列表(不分页，下拉框使用)
	 * @param tokenVo
	 * @param param
	 * @return
	 * @throws AlertException
	 */
    List<SystemRoleDto> getSystemRoleListNoPage() throws AlertException;
    
    /**
     * 新增
     * @param addressBody
     * @param tokenVo
     * @throws AlertException
     */
    void addSystemRole(SystemRoleVo sysRoleVo, TokenVo tokenVo) throws AlertException;
    
    /**
     * 修改
     * @param addressBody
     * @param tokenVo
     * @throws AlertException
     */
    void updateSystemRole(SystemRoleVo sysRoleVo, TokenVo tokenVo) throws AlertException;
    
    /**
     * 详情
     * @param tokenVo
     * @param id
     * @return
     * @throws AlertException
     */
    SystemRoleVo getSystemRoleDetail(TokenVo tokenVo, String id) throws AlertException;
    
    /**
     * 删除
     * @param tokenVo
     * @param id
     * @return
     * @throws AlertException
     */
    void delSystemRole(String id) throws AlertException;
    
    /**
     * 权限配置
     * @param tokenVo
     * @param id
     * @return
     * @throws AlertException
     */
    void authorizationConfig(TokenVo tokenVo, AuthorizationConfigInfo param) throws AlertException;
    

}
