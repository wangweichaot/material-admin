package com.sjlexpress.wl.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sjlexpress.wl.bean.BaseMapper;
import com.sjlexpress.wl.dto.SystemUserDto;
import com.sjlexpress.wl.param.SystemUserParam;
import com.sjlexpress.wl.pojo.SystemUser;

public interface SystemUserMapper extends BaseMapper<SystemUser> {
	
	List<SystemUserDto> getSysUserList(SystemUserParam param);
	
	SystemUserDto getSysUserDetail(String id);
	
	/**
	 * 检查登录用户名是否存在
	 * @param userAccount
	 * @return
	 */
	int selectByUserAccount(@Param("operFlag")String operFlag, @Param("id") String id, @Param("userAccount")String userAccount);
	
}