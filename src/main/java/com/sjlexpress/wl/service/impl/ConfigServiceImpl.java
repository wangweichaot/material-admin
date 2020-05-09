package com.sjlexpress.wl.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sjlexpress.wl.dao.ConfigMapper;
import com.sjlexpress.wl.dto.ConfigDto;
import com.sjlexpress.wl.exception.AlertException;
import com.sjlexpress.wl.param.ConfigParam;
import com.sjlexpress.wl.pojo.Config;
import com.sjlexpress.wl.service.IConfigService;
import com.sjlexpress.wl.util.StringUtil;
import com.sjlexpress.wl.vo.ConfigUpdateVo;
import com.sjlexpress.wl.vo.ConfigVo;
import com.sjlexpress.wl.vo.PageVo;
import com.sjlexpress.wl.vo.TokenVo;

/**
 * 功能描述: 公共配置管理接口实现层
 * @author wangweichao
 */
@Service
public class ConfigServiceImpl implements IConfigService {
	
    @Autowired
    ConfigMapper configMapper;

    /**
     * 分页查询公共配置列表
     */
	@Override
	public PageVo<ConfigDto> getConfigList(TokenVo tokenVo, ConfigParam param) throws AlertException {
		param.pageHelper();
        List<ConfigDto> ConfigList = configMapper.getConfigList(param);
        return new PageVo<>(ConfigList, param);
	}
	
	/**
	 * 唯一性校验：name+value不能重复
	 * @param operFlag 1新增  2修改
	 * @param id
	 * @param name
	 * @param value
	 */
	private void checkConfig(String operFlag, String id, String name, String value) {
		Config Config = configMapper.selectConfig(operFlag, id, name, value);
		if(Config !=null) {
			throw new AlertException("名称+值不能重复");
		}
	}
	
	/**
	 * 新增
	 */
	@Override
	public void addConfig(ConfigVo ConfigVo, TokenVo tokenVo) throws AlertException {
		
		this.checkConfig("1", null, ConfigVo.getName(), ConfigVo.getValue());
		
		Config Config = new Config();
		BeanUtils.copyProperties(ConfigVo, Config);
		String id = StringUtil.getUUid();
		Config.setId(id);
		Config.setCreateUser(tokenVo.getId());
		Config.setCreateTime(new Date());
		int i = configMapper.insertSelective(Config);
		if(i < 1) {
			throw new AlertException("新增公共配置失败");
		}
		
	}

	/**
	 * 修改
	 */
	@Override
	public void updateConfig(ConfigVo ConfigVo, TokenVo tokenVo) throws AlertException {
		
		this.checkConfig("2", ConfigVo.getId(), ConfigVo.getName(), ConfigVo.getValue());
		
		Config Config = new Config();
		BeanUtils.copyProperties(ConfigVo, Config);
		Config.setUpdateUser(tokenVo.getId());
		Config.setUpdateTime(new Date());
		int i = configMapper.updateByPrimaryKeySelective(Config);
		if(i < 1) {
			throw new AlertException("更新公共配置失败");
		}
		
	}
	
	/**
	 * 详情
	 */
	@Override
	public ConfigVo getConfigDetail(TokenVo tokenVo, String id) throws AlertException {
		Config Config = configMapper.selectByPrimaryKey(id);
		ConfigVo ConfigVo = new ConfigVo();
		BeanUtils.copyProperties(Config, ConfigVo);
		return ConfigVo;
	}
	
	/**
	 * 启用/禁用
	 */
	@Override
	public void openCloseConfig(ConfigUpdateVo ConfigUpdateVo, TokenVo tokenVo) throws AlertException {
		Config Config = new Config();
		Config.setId(ConfigUpdateVo.getId());
		String operFlag = ConfigUpdateVo.getOperFlag();
		if("0".equals(operFlag)) {//启用
			Config.setIsDelete(0);
		}else if("1".equals(operFlag)){//禁用
			Config.setIsDelete(1);
		}else {
			throw new AlertException("操作标志非法");
		}
		Config.setUpdateUser(tokenVo.getId());
		Config.setUpdateTime(new Date());
		int i = configMapper.updateByPrimaryKeySelective(Config);
		if(i < 1) {
			throw new AlertException("更新公共配置状态失败");
		}
	}
	
}
