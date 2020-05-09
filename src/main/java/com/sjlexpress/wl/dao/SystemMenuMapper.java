package com.sjlexpress.wl.dao;

import java.util.List;

import com.sjlexpress.wl.bean.BaseMapper;
import com.sjlexpress.wl.dto.SystemMenuDto;
import com.sjlexpress.wl.param.SystemMenuParam;
import com.sjlexpress.wl.pojo.SystemMenu;
import com.sjlexpress.wl.vo.SystemMenuVo;

public interface SystemMenuMapper extends BaseMapper<SystemMenu> {
	
	/**
	 * 迭代查询（父级查询）
	 * @param param
	 * @return
	 */
	List<SystemMenuDto> getSystemMenuList(SystemMenuParam param);
	
	/**
	 * 迭代查询（父级查询）
	 * @param param
	 * @return
	 */
	List<SystemMenuVo> getSystemMenuListByUserId(String userId);
	
	/**
	 * 查询所有菜单列表，无层级(超级管理员初始化使用，写入system_role_menu)
	 * @param param
	 * @return
	 */
	List<SystemMenuDto> getSystemMenuAllList();
	
}