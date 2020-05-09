package com.sjlexpress.wl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sjlexpress.wl.dto.SystemRoleDto;
import com.sjlexpress.wl.exception.AlertException;
import com.sjlexpress.wl.param.SystemRoleParam;
import com.sjlexpress.wl.service.ISystemRoleService;
import com.sjlexpress.wl.util.UserUtil;
import com.sjlexpress.wl.vo.PageVo;
import com.sjlexpress.wl.vo.RespJson;
import com.sjlexpress.wl.vo.SystemRoleVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 功能描述: 角色管理
 * @author wangweichao
 * @date 2020-5-7
 */
@Api(value = "/systemRole", tags = "角色管理")
@RestController
@RequestMapping("/systemRole")
@CrossOrigin
public class SystemRoleController {

    @Autowired
    private ISystemRoleService systemRoleService;

    @ApiOperation(value = "分页查询角色列表")
    @ApiImplicitParam(name = "Authorization", value = "登录获取的token", required = true, dataType = "string", paramType = "header")
    @GetMapping("/getSystemRoleList")
    public RespJson<PageVo<SystemRoleDto>> getSystemRoleList(SystemRoleParam param) throws AlertException {
        PageVo<SystemRoleDto> list = systemRoleService.getSystemRoleList(UserUtil.get(), param);
        return RespJson.success(list);
    }
    
    @ApiOperation(value = "查询列表(不分页，下拉框使用)")
    @ApiImplicitParam(name = "Authorization", value = "登录获取的token", required = true, dataType = "string", paramType = "header")
    @GetMapping("/getSystemRoleListNoPage")
    public RespJson<List<SystemRoleDto>> getSystemRoleListNoPage() throws AlertException {
        List<SystemRoleDto> list = systemRoleService.getSystemRoleListNoPage();
        return RespJson.success(list);
    }
    
    @ApiOperation(value = "新增角色并赋予菜单权限")
    @ApiImplicitParam(name = "Authorization", value = "登录获取的token", required = true, dataType = "string", paramType = "header")
    @PostMapping("/addSystemRole")
    public RespJson<Boolean> addSystemRole(@RequestBody @Validated SystemRoleVo param) throws AlertException {
    	systemRoleService.addSystemRole(param, UserUtil.get());
        return RespJson.success(true);
    }
    
    @ApiOperation(value = "修改角色并赋予菜单权限")
    @ApiImplicitParam(name = "Authorization", value = "登录获取的token", required = true, dataType = "string", paramType = "header")
    @PostMapping("/updateSystemRole")
    public RespJson<Boolean> updateSystemRole(@RequestBody @Validated SystemRoleVo param) throws AlertException {
    	systemRoleService.updateSystemRole(param, UserUtil.get());
        return RespJson.success(true);
    }
    
    @ApiOperation(value = "查询角色详情")
    @ApiImplicitParam(name = "Authorization", value = "登录获取的token", required = true, dataType = "string", paramType = "header")
    @GetMapping("/getSystemRoleDetail")
    public RespJson<SystemRoleVo> getSystemRoleDetail(@RequestParam String id) throws AlertException {
    	SystemRoleVo detail = systemRoleService.getSystemRoleDetail(UserUtil.get(), id);
        return RespJson.success(detail);
    }
    
    @ApiOperation(value = "删除角色接口")
    @ApiImplicitParam(name = "Authorization", value = "登录获取的token", required = true, dataType = "string", paramType = "header")
    @GetMapping("/delSystemRole")
    public RespJson<Boolean> delSystemRole(@RequestParam String id) throws AlertException {
    	systemRoleService.delSystemRole(id);
        return RespJson.success(true);
    }
    
//    @ApiOperation(value = "权限配置接口")
//    @ApiImplicitParam(name = "Authorization", value = "登录获取的token", required = true, dataType = "string", paramType = "header")
//    @PostMapping("/authorizationConfig")
//    public RespJson<Boolean> authorizationConfig(@RequestBody @Validated AuthorizationConfigInfo param) throws AlertException {
//    	systemRoleService.authorizationConfig(UserUtil.get(), param);
//        return RespJson.success(true);
//    }

}
