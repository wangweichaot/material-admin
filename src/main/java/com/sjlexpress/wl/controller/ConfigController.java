package com.sjlexpress.wl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sjlexpress.wl.dto.ConfigDto;
import com.sjlexpress.wl.exception.AlertException;
import com.sjlexpress.wl.param.ConfigParam;
import com.sjlexpress.wl.service.IConfigService;
import com.sjlexpress.wl.util.UserUtil;
import com.sjlexpress.wl.vo.ConfigUpdateVo;
import com.sjlexpress.wl.vo.ConfigVo;
import com.sjlexpress.wl.vo.PageVo;
import com.sjlexpress.wl.vo.RespJson;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 功能描述: 公共配置管理
 * @author wangweichao
 * @date 2020-5-8
 */
@Api(value = "/config", tags = "公共配置管理")
@RestController
@RequestMapping("/config")
@CrossOrigin
public class ConfigController {

    @Autowired
    private IConfigService configService;

    @ApiOperation(value = "分页查询公共配置列表")
    @ApiImplicitParam(name = "Authorization", value = "登录获取的token", required = true, dataType = "string", paramType = "header")
    @GetMapping("/getConfigList")
    public RespJson<PageVo<ConfigDto>> getConfigList(ConfigParam param) throws AlertException {
        PageVo<ConfigDto> list = configService.getConfigList(UserUtil.get(), param);
        return RespJson.success(list);
    }
    
    @ApiOperation(value = "新增公共配置")
    @ApiImplicitParam(name = "Authorization", value = "登录获取的token", required = true, dataType = "string", paramType = "header")
    @PostMapping("/addConfig")
    public RespJson<Boolean> addConfig(@RequestBody @Validated ConfigVo param) throws AlertException {
    	configService.addConfig(param, UserUtil.get());
        return RespJson.success(true);
    }
    
    @ApiOperation(value = "修改公共配置")
    @ApiImplicitParam(name = "Authorization", value = "登录获取的token", required = true, dataType = "string", paramType = "header")
    @PostMapping("/updateConfig")
    public RespJson<Boolean> updateConfig(@RequestBody @Validated ConfigVo param) throws AlertException {
    	configService.updateConfig(param, UserUtil.get());
        return RespJson.success(true);
    }
    
    @ApiOperation(value = "查询公共配置详情")
    @ApiImplicitParam(name = "Authorization", value = "登录获取的token", required = true, dataType = "string", paramType = "header")
    @GetMapping("/getConfigDetail")
    public RespJson<ConfigVo> getConfigDetail(@RequestParam String id) throws AlertException {
    	ConfigVo detail = configService.getConfigDetail(UserUtil.get(), id);
        return RespJson.success(detail);
    }
    
    @ApiOperation(value = "公共配置启用/禁用接口")
    @ApiImplicitParam(name = "Authorization", value = "登录获取的token", required = true, dataType = "string", paramType = "header")
    @PostMapping("/openCloseConfig")
    public RespJson<Boolean> openCloseConfig(@RequestBody @Validated ConfigUpdateVo param) throws AlertException {
    	configService.openCloseConfig(param, UserUtil.get());
        return RespJson.success(true);
    }


}
