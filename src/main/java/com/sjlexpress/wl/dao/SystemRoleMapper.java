package com.sjlexpress.wl.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sjlexpress.wl.bean.BaseMapper;
import com.sjlexpress.wl.dto.SystemRoleDto;
import com.sjlexpress.wl.param.SystemRoleParam;
import com.sjlexpress.wl.pojo.SystemRole;

/**
 * 角色管理DAO层
 *
 */
@Mapper
public interface SystemRoleMapper extends BaseMapper<SystemRole> {
	
	
	List<SystemRoleDto> getSystemRoleList(SystemRoleParam param);
	
	int checkSystemRoleInfo(@Param("operFlag") String operFlag, @Param("id") Integer id, @Param("roleName") String roleName);
	
	int insertSystemRole(SystemRole systemRole);
	
}