package com.sjlexpress.wl.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.sjlexpress.wl.dao.SystemMenuMapper;
import com.sjlexpress.wl.dto.SystemMenuDto;
import com.sjlexpress.wl.exception.AlertException;
import com.sjlexpress.wl.param.SystemMenuParam;
import com.sjlexpress.wl.pojo.SystemMenu;
import com.sjlexpress.wl.service.ISystemMenuService;
import com.sjlexpress.wl.util.StringUtilsWL;
import com.sjlexpress.wl.vo.SystemMenuUpdateVo;
import com.sjlexpress.wl.vo.SystemMenuVo;
import com.sjlexpress.wl.vo.PageVo;
import com.sjlexpress.wl.vo.TokenVo;

/**
 * 功能描述: 菜单管理接口实现层
 * @author wangweichao
 */
@Service
public class SystemMenuServiceImpl implements ISystemMenuService {
	
    @Autowired
    SystemMenuMapper systemMenuMapper;
    
    /**
     * 分页查询菜单列表
     */
	@Override
	public PageVo<SystemMenuDto> getSystemMenuList(TokenVo tokenVo, SystemMenuParam param) throws AlertException {
		param.pageHelper();
        List<SystemMenuDto> SystemMenuList = systemMenuMapper.getSystemMenuList(param);
        return new PageVo<>(SystemMenuList, param);
	}
	
    /**
     * 查询菜单列表(不分页，ztree使用)
     */
	@Override
	public List<SystemMenuDto> getSystemMenuListNoPage() throws AlertException {
        List<SystemMenuDto> SystemMenuList = systemMenuMapper.getSystemMenuList(null);
        return SystemMenuList;
	}
	
	/**
	 * 新增
	 */
	@Override
	public void addSystemMenu(SystemMenuVo SystemMenuVo, TokenVo tokenVo) throws AlertException {
		SystemMenu SystemMenu = new SystemMenu();
		BeanUtils.copyProperties(SystemMenuVo, SystemMenu);
		SystemMenu.setCreateUser(tokenVo.getId());
		SystemMenu.setCreateTime(new Date());
		int i = systemMenuMapper.insertSelective(SystemMenu);
		if(i < 1) {
			throw new AlertException("新增菜单失败");
		}
		
	}

	/**
	 * 修改
	 */
	@Override
	public void updateSystemMenu(SystemMenuVo SystemMenuVo, TokenVo tokenVo) throws AlertException {
		SystemMenu SystemMenu = new SystemMenu();
		BeanUtils.copyProperties(SystemMenuVo, SystemMenu);
		SystemMenu.setUpdateUser(tokenVo.getId());
		SystemMenu.setUpdateTime(new Date());
		int i = systemMenuMapper.updateByPrimaryKeySelective(SystemMenu);
		if(i < 1) {
			throw new AlertException("更新菜单失败");
		}
		
	}
	
	/**
	 * 详情
	 */
	@Override
	public SystemMenuVo getSystemMenuDetail(TokenVo tokenVo, String id) throws AlertException {
		SystemMenu SystemMenu = systemMenuMapper.selectByPrimaryKey(id);
		SystemMenuVo SystemMenuVo = new SystemMenuVo();
		BeanUtils.copyProperties(SystemMenu, SystemMenuVo);
		return SystemMenuVo;
	}
	
	/**
	 * 启用/禁用
	 */
	@Override
	public void openCloseSystemMenu(SystemMenuUpdateVo SystemMenuUpdateVo, TokenVo tokenVo) throws AlertException {
		SystemMenu SystemMenu = new SystemMenu();
		SystemMenu.setId(Integer.valueOf(SystemMenuUpdateVo.getId()));
		String operFlag = SystemMenuUpdateVo.getOperFlag();
		if("0".equals(operFlag)) {//启用
			SystemMenu.setIsDelete(0);
		}else if("1".equals(operFlag)){//禁用
			SystemMenu.setIsDelete(1);
		}else {
			throw new AlertException("操作标志非法");
		}
		SystemMenu.setUpdateUser(tokenVo.getId());
		SystemMenu.setUpdateTime(new Date());
		int i = systemMenuMapper.updateByPrimaryKeySelective(SystemMenu);
		if(i < 1) {
			throw new AlertException("更新菜单状态失败");
		}
	}
	
	/**
	 * 删除
	 */
	@Override
	public void delSystemMenu(Integer id) throws AlertException {
		if(StringUtilsWL.isEmpty(id)) {
			throw new AlertException("主键ID不能为空");
		}
		
		SystemMenu hytVersion = systemMenuMapper.selectByPrimaryKey(id);
		
		if(null == hytVersion) {
			throw new AlertException("尝试删除的菜单信息已不存在");
		}
		
		int count = systemMenuMapper.deleteByPrimaryKey(id);
		
		if(count < 1) {
			throw new AlertException("删除菜单信息失败");
		}
		
	}

	/**
	 * 查询所有菜单列表(不分页，无层级)
	 */
	@Override
	public List<SystemMenuDto> getSystemMenuAllList() throws AlertException {
		return systemMenuMapper.getSystemMenuAllList();
	}

}
