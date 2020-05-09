package com.sjlexpress.wl.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sjlexpress.wl.dao.SystemMenuMapper;
import com.sjlexpress.wl.dao.SystemRoleMapper;
import com.sjlexpress.wl.dao.SystemRoleMenuMapper;
import com.sjlexpress.wl.dao.SystemUserMapper;
import com.sjlexpress.wl.dao.SystemUserRoleMapper;
import com.sjlexpress.wl.dto.SystemMenuDto;
import com.sjlexpress.wl.dto.SystemRoleDto;
import com.sjlexpress.wl.entity.AuthorizationConfigInfo;
import com.sjlexpress.wl.exception.AlertException;
import com.sjlexpress.wl.param.SystemRoleParam;
import com.sjlexpress.wl.pojo.SystemRole;
import com.sjlexpress.wl.pojo.SystemRoleMenu;
import com.sjlexpress.wl.service.ISystemMenuService;
import com.sjlexpress.wl.service.ISystemRoleService;
import com.sjlexpress.wl.util.StringUtil;
import com.sjlexpress.wl.util.StringUtilsWL;
import com.sjlexpress.wl.vo.PageVo;
import com.sjlexpress.wl.vo.SystemRoleVo;
import com.sjlexpress.wl.vo.TokenVo;

/**
 * 功能描述: 角色管理接口实现层
 * @author wangweichao
 */
@Service
public class SystemRoleServiceImpl implements ISystemRoleService {
	
    @Autowired
    SystemUserMapper systemUserMapper;
    @Autowired
    SystemRoleMenuMapper systemRoleMenuMapper;
    @Autowired
    SystemMenuMapper systemMenuMapper;
    @Autowired
    SystemUserRoleMapper systemUserRoleMapper;
    @Autowired
    SystemRoleMapper systemRoleMapper;
    @Autowired
    ISystemMenuService systemMenuService;

    /**
     * 分页查询角色列表
     */
	@Override
	public PageVo<SystemRoleDto> getSystemRoleList(TokenVo tokenVo, SystemRoleParam param) throws AlertException {
		param.pageHelper();
        List<SystemRoleDto> SystemRoleList = systemRoleMapper.getSystemRoleList(param);
        return new PageVo<>(SystemRoleList, param);
	}
	
	/**
     * 查询角色列表(不分页，下拉框使用)
     */
	@Override
	public List<SystemRoleDto> getSystemRoleListNoPage() throws AlertException {
        List<SystemRoleDto> SystemRoleList = systemRoleMapper.getSystemRoleList(null);
        return SystemRoleList;
	}
	
	/**
	 * 新增角色并赋予菜单权限
	 */
	@Override
	@Transactional
	public void addSystemRole(SystemRoleVo systemRoleVo, TokenVo tokenVo) throws AlertException {
		
		//1、唯一性校验
		this.checkSystemRoleInfo("1", null, systemRoleVo.getRoleName());
		
		//2、插入基表
		SystemRole systemRole = new SystemRole();
		BeanUtils.copyProperties(systemRoleVo, systemRole);
//		String id = StringUtil.getUUid();
//		SystemRole.setId(id);使用自增主键
		systemRole.setCreateUser(tokenVo.getId());
		systemRole.setCreateTime(new Date());
		systemRole.setIsDelete(0);//启用
		int i = systemRoleMapper.insertSystemRole(systemRole);
		if(i < 1) {
			throw new AlertException("新增角色失败");
		}
		
		//3、绑定菜单权限
		if(StringUtilsWL.isEmpty(systemRoleVo.getIds())) {
			throw new AlertException("请勾选菜单赋予角色权限");
		}
		String[] ids = systemRoleVo.getIds().split(",");
		for(String menuId : ids) {
			//插入system_role_menu
			SystemRoleMenu systemRoleMenu = new SystemRoleMenu();
			String rmid = StringUtil.getUUid();
			systemRoleMenu.setId(rmid);
			systemRoleMenu.setMenuId(menuId);
			systemRoleMenu.setRoleId(systemRole.getId());
			systemRoleMenu.setCreateUser(tokenVo.getId());
			systemRoleMenu.setCreateTime(new Date());
			systemRoleMenuMapper.insert(systemRoleMenu);
		}
	}
	
	/**
	 * 角色名称不能重复
	 * @param operFlag 1新增 2修改
	 * @param id
	 * @param warehouseId
	 * @return
	 */
	private void checkSystemRoleInfo(String operFlag, Integer id, String roleName) {
		int count = systemRoleMapper.checkSystemRoleInfo(operFlag, id, roleName);
		if(count > 0) {
			throw new AlertException("角色名称不能重复");
		}
		
	}

	/**
	 * 修改角色并赋予菜单权限
	 */
	@Override
	@Transactional
	public void updateSystemRole(SystemRoleVo systemRoleVo, TokenVo tokenVo) throws AlertException {
		
		//1、唯一性校验
		this.checkSystemRoleInfo("2", systemRoleVo.getId(), systemRoleVo.getRoleName());
		
		//2、修改基表
		SystemRole systemRole = new SystemRole();
		BeanUtils.copyProperties(systemRoleVo, systemRole);
		systemRole.setUpdateUser(tokenVo.getId());
		systemRole.setUpdateTime(new Date());
		int i = systemRoleMapper.updateByPrimaryKeySelective(systemRole);
		if(i < 1) {
			throw new AlertException("更新角色失败");
		}
		
		//3、删除原有权限关系（先这么暴力处理...）
		int num = systemRoleMenuMapper.delSystemRoleMenuByRoleId(systemRoleVo.getId()+"");
//		if(num < 1) {
//			throw new AlertException("解除原权限关系失败");
//		}
		
		//4、重新绑定菜单权限
		if(StringUtilsWL.isEmpty(systemRoleVo.getIds())) {
			throw new AlertException("请勾选菜单赋予角色权限");
		}
		String[] ids = systemRoleVo.getIds().split(",");
		for(String menuId : ids) {
			//插入system_role_menu
			SystemRoleMenu systemRoleMenu = new SystemRoleMenu();
			String rmid = StringUtil.getUUid();
			systemRoleMenu.setId(rmid);
			systemRoleMenu.setMenuId(menuId);
			systemRoleMenu.setRoleId(systemRole.getId());
			systemRoleMenu.setCreateUser(tokenVo.getId());
			systemRoleMenu.setCreateTime(new Date());
			systemRoleMenuMapper.insert(systemRoleMenu);
		}
		
	}
	
	/**
	 * 详情
	 */
	@Override
	public SystemRoleVo getSystemRoleDetail(TokenVo tokenVo, String id) throws AlertException {
		SystemRole SystemRole = systemRoleMapper.selectByPrimaryKey(id);
		SystemRoleVo SystemRoleVo = new SystemRoleVo();
		BeanUtils.copyProperties(SystemRole, SystemRoleVo);
		return SystemRoleVo;
	}
	
	/**
	 * 删除
	 */
	@Override
	public void delSystemRole(String id) throws AlertException {
		if(StringUtilsWL.isEmpty(id)) {
			throw new AlertException("主键ID不能为空");
		}
		
		SystemRole systemRole = systemRoleMapper.selectByPrimaryKey(id);
		
		if(null == systemRole) {
			throw new AlertException("尝试删除的角色信息已不存在");
		}
		
		int count = systemRoleMapper.deleteByPrimaryKey(id);
		
		if(count < 1) {
			throw new AlertException("删除角色信息失败");
		}
		
	}

	/**
	 * 权限配置
	 */
	@Override
	public void authorizationConfig(TokenVo tokenVo, AuthorizationConfigInfo param) throws AlertException {
		//1、更新system_role表
		
		//2、插入system_role_menu表
	}


}
