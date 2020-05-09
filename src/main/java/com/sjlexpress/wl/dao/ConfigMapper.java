package com.sjlexpress.wl.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sjlexpress.wl.bean.BaseMapper;
import com.sjlexpress.wl.dto.ConfigDto;
import com.sjlexpress.wl.param.ConfigParam;
import com.sjlexpress.wl.pojo.Config;

public interface ConfigMapper extends BaseMapper<Config> {
	
	List<ConfigDto> getConfigList(ConfigParam param);
	
	Config selectConfig(@Param("operFlag") String operFlag, @Param("id") String id, 
			@Param("name") String name, @Param("value") String value);
	
}