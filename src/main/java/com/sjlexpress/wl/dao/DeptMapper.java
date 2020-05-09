package com.sjlexpress.wl.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sjlexpress.wl.bean.BaseMapper;
import com.sjlexpress.wl.dto.DeptDto;
import com.sjlexpress.wl.param.DeptParam;
import com.sjlexpress.wl.pojo.Dept;

public interface DeptMapper extends BaseMapper<Dept> {
	
	List<DeptDto> getDeptList(DeptParam param);
	
	List<Dept> getDeptListNoPage();
	
	int checkDeptInfo(@Param("operFlag") String operFlag, @Param("id") Integer id, @Param("name") String name);
	
}