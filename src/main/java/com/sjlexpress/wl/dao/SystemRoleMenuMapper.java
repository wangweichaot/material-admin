package com.sjlexpress.wl.dao;

import com.sjlexpress.wl.bean.BaseMapper;
import com.sjlexpress.wl.pojo.SystemRoleMenu;

public interface SystemRoleMenuMapper extends BaseMapper<SystemRoleMenu> {
	
	int delSystemRoleMenuByRoleId(String roleId);
}