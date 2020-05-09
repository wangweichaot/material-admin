package com.sjlexpress.wl.service;

import java.util.List;

import com.sjlexpress.wl.dto.SystemUserDto;
import com.sjlexpress.wl.exception.AlertException;
import com.sjlexpress.wl.param.LoginParam;
import com.sjlexpress.wl.param.SystemUserParam;
import com.sjlexpress.wl.vo.PageVo;
import com.sjlexpress.wl.vo.SysUserVo;
import com.sjlexpress.wl.vo.SystemMenuVo;
import com.sjlexpress.wl.vo.SystemUserUpdateVo;
import com.sjlexpress.wl.vo.SystemUserVo;
import com.sjlexpress.wl.vo.TokenVo;

/**
 * 用户服务接口
 *
 * @author zhicheng.lai
 */
public interface ISysUserService {

    public SysUserVo adminLogin(LoginParam param);
    
    public SysUserVo adminRegister(LoginParam param);
    
    /**
     * 超级管理员初始化
     * @return
     */
    public void adminInit();
    
    /**
	 * 分页查询员工列表
	 * @param tokenVo
	 * @param param
	 * @return
	 * @throws AlertException
	 */
    PageVo<SystemUserDto> getSysUserList(TokenVo tokenVo, SystemUserParam param) throws AlertException;
    
    /**
	 * 加载当前登录用户菜单列表
	 * @param tokenVo
	 * @param param
	 * @return
	 * @throws AlertException
	 */
    List<SystemMenuVo> getMenuList(String userId) throws AlertException;
    
    /**
     * 新增
     * @param addressBody
     * @param tokenVo
     * @throws AlertException
     */
    void addSysUser(SystemUserVo sysRoleVo, TokenVo tokenVo) throws AlertException;
    
    /**
     * 修改
     * @param addressBody
     * @param tokenVo
     * @throws AlertException
     */
    void updateSysUser(SystemUserVo sysRoleVo, TokenVo tokenVo) throws AlertException;
    
    /**
     * 详情
     * @param tokenVo
     * @param id
     * @return
     * @throws AlertException
     */
    SystemUserDto getSysUserDetail(TokenVo tokenVo, String id) throws AlertException;
    
    /**
     * 启用/禁用
     * @param addressBody
     * @param tokenVo
     * @throws AlertException
     */
    void openCloseSysUser(SystemUserUpdateVo sysRoleVo, TokenVo tokenVo) throws AlertException;
    
    /**
     * 重置密码接口
     * @param addressBody
     * @param tokenVo
     * @throws AlertException
     */
    void resetPswd(SystemUserVo sysRoleVo, TokenVo tokenVo) throws AlertException;
    
    /**
     * 修改密码接口
     * @param addressBody
     * @param tokenVo
     * @throws AlertException
     */
    void updatePswd(SystemUserVo sysRoleVo, TokenVo tokenVo) throws AlertException;

}
