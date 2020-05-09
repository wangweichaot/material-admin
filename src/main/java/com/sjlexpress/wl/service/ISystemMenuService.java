package com.sjlexpress.wl.service;

import java.util.List;

import com.sjlexpress.wl.dto.SystemMenuDto;
import com.sjlexpress.wl.exception.AlertException;
import com.sjlexpress.wl.param.SystemMenuParam;
import com.sjlexpress.wl.vo.SystemMenuUpdateVo;
import com.sjlexpress.wl.vo.SystemMenuVo;
import com.sjlexpress.wl.vo.PageVo;
import com.sjlexpress.wl.vo.TokenVo;

/**
 * 菜单管理接口层
 */
public interface ISystemMenuService {

	/**
	 * 分页查询菜单列表
	 * @param tokenVo
	 * @param param
	 * @return
	 * @throws AlertException
	 */
    PageVo<SystemMenuDto> getSystemMenuList(TokenVo tokenVo, SystemMenuParam param) throws AlertException;
    
    /**
	 * 查询菜单列表(不分页，下拉框使用)
	 * @param tokenVo
	 * @param param
	 * @return
	 * @throws AlertException
	 */
    List<SystemMenuDto> getSystemMenuListNoPage() throws AlertException;
    
    /**
     * 新增
     * @param addressBody
     * @param tokenVo
     * @throws AlertException
     */
    void addSystemMenu(SystemMenuVo sysRoleVo, TokenVo tokenVo) throws AlertException;
    
    /**
     * 修改
     * @param addressBody
     * @param tokenVo
     * @throws AlertException
     */
    void updateSystemMenu(SystemMenuVo sysRoleVo, TokenVo tokenVo) throws AlertException;
    
    /**
     * 详情
     * @param tokenVo
     * @param id
     * @return
     * @throws AlertException
     */
    SystemMenuVo getSystemMenuDetail(TokenVo tokenVo, String id) throws AlertException;
    
    /**
     * 启用/禁用
     * @param addressBody
     * @param tokenVo
     * @throws AlertException
     */
    void openCloseSystemMenu(SystemMenuUpdateVo sysRoleVo, TokenVo tokenVo) throws AlertException;
    
    /**
     * 删除
     * @param tokenVo
     * @param id
     * @return
     * @throws AlertException
     */
    void delSystemMenu(Integer id) throws AlertException;
    
    /**
	 * 查询所有菜单列表(不分页，无层级)
	 * @param tokenVo
	 * @param param
	 * @return
	 * @throws AlertException
	 */
    List<SystemMenuDto> getSystemMenuAllList() throws AlertException;
    


}
