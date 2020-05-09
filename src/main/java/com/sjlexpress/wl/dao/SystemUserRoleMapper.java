package com.sjlexpress.wl.dao;

import org.apache.ibatis.annotations.Param;

import com.sjlexpress.wl.bean.BaseMapper;
import com.sjlexpress.wl.pojo.SystemUserRole;

public interface SystemUserRoleMapper extends BaseMapper<SystemUserRole> {
	
	int updateRoleIdByUserId(@Param("roleId") String roleId, @Param("userId") String userId);
}