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

import com.sjlexpress.wl.dto.SystemUserDto;
import com.sjlexpress.wl.exception.AlertException;
import com.sjlexpress.wl.param.SystemUserParam;
import com.sjlexpress.wl.pojo.Dept;
import com.sjlexpress.wl.param.LoginParam;
import com.sjlexpress.wl.service.ISysUserService;
import com.sjlexpress.wl.util.UserUtil;
import com.sjlexpress.wl.vo.SystemUserUpdateVo;
import com.sjlexpress.wl.vo.SystemUserVo;
import com.sjlexpress.wl.vo.PageVo;
import com.sjlexpress.wl.vo.RespJson;
import com.sjlexpress.wl.vo.SysUserVo;
import com.sjlexpress.wl.vo.SystemMenuVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;


/**
 * 功能描述: 用户控制器
 * @author wangweichao
 * @date 2020-5-7
 */
@Api(value = "/sys_user", tags = "后台用户")
@RestController
@RequestMapping("/sys_user")
@CrossOrigin
public class SysUserController {

    @Autowired
    private ISysUserService sysUserService;

    @ApiOperation(value = "后台登录获取token")
    @PostMapping("/sysLogin")
    public RespJson<SysUserVo> appLogin(@RequestBody @Validated LoginParam param) throws AlertException {
    	SysUserVo user = sysUserService.adminLogin(param);
        return RespJson.success(user);
    }
    
    /**
     * 当前仅通过后台新增注册，前端注册入口暂不开放
     * @param param
     * @return
     * @throws AlertException
     */
    @ApiOperation(value = "后台注册")
    @PostMapping("/sysRegister")
    public RespJson<SysUserVo> appRegister(@RequestBody @Validated LoginParam param) throws AlertException {
    	SysUserVo user = sysUserService.adminRegister(param);
        return RespJson.success(user);
    }
    
    /**
     * 接口作用：通过Postman调用，前端不提供点击入口。即系统第一次运行时，admin加载所有菜单，并具有所有菜单权限
     * 实现思路：插入默认值system_user、system_user_role、system_role、system_role_menu、system_menu
     * @param param
     * @return
     * @throws AlertException
     */
    @ApiOperation(value = "超级管理员初始化接口")
    @PostMapping("/adminInit")
    public void adminInit() throws AlertException {
        sysUserService.adminInit();
    }
    
    @ApiOperation(value = "分页查询员工列表")
    @ApiImplicitParam(name = "Authorization", value = "登录获取的token", required = true, dataType = "string", paramType = "header")
    @GetMapping("/getSysUserList")
    public RespJson<PageVo<SystemUserDto>> getSysUserList(SystemUserParam param) throws AlertException {
        PageVo<SystemUserDto> list = sysUserService.getSysUserList(UserUtil.get(), param);
        return RespJson.success(list);
    }
    
    @ApiOperation(value = "加载当前登录用户菜单列表")
    @ApiImplicitParam(name = "Authorization", value = "登录获取的token", required = true, dataType = "string", paramType = "header")
    @GetMapping("/getMenuList")
    public RespJson<List<SystemMenuVo>> getMenuList(@RequestParam String id) throws AlertException {
    	List<SystemMenuVo> list = sysUserService.getMenuList(id);
        return RespJson.success(list);
    }
    
    @ApiOperation(value = "新增员工")
    @ApiImplicitParam(name = "Authorization", value = "登录获取的token", required = true, dataType = "string", paramType = "header")
    @PostMapping("/addSysUser")
    public RespJson<Boolean> addSysUser(@RequestBody @Validated SystemUserVo param) throws AlertException {
    	sysUserService.addSysUser(param, UserUtil.get());
        return RespJson.success(true);
    }
    
    @ApiOperation(value = "修改员工")
    @ApiImplicitParam(name = "Authorization", value = "登录获取的token", required = true, dataType = "string", paramType = "header")
    @PostMapping("/updateSysUser")
    public RespJson<Boolean> updateSysUser(@RequestBody @Validated SystemUserVo param) throws AlertException {
    	sysUserService.updateSysUser(param, UserUtil.get());
        return RespJson.success(true);
    }
    
    @ApiOperation(value = "查询员工详情")
    @ApiImplicitParam(name = "Authorization", value = "登录获取的token", required = true, dataType = "string", paramType = "header")
    @GetMapping("/getSysUserDetail")
    public RespJson<SystemUserDto> getSysUserDetail(@RequestParam String id) throws AlertException {
    	SystemUserDto detail = sysUserService.getSysUserDetail(UserUtil.get(), id);
        return RespJson.success(detail);
    }
    
    @ApiOperation(value = "员工启用/禁用接口")
    @ApiImplicitParam(name = "Authorization", value = "登录获取的token", required = true, dataType = "string", paramType = "header")
    @PostMapping("/openCloseSysUser")
    public RespJson<Boolean> openCloseSysUser(@RequestBody @Validated SystemUserUpdateVo param) throws AlertException {
    	sysUserService.openCloseSysUser(param, UserUtil.get());
        return RespJson.success(true);
    }
    
    @ApiOperation(value = "重置密码接口")
    @ApiImplicitParam(name = "Authorization", value = "登录获取的token", required = true, dataType = "string", paramType = "header")
    @PostMapping("/resetPswd")
    public RespJson<Boolean> resetPswd(@RequestBody @Validated SystemUserVo param) throws AlertException {
    	sysUserService.resetPswd(param, UserUtil.get());
        return RespJson.success(true);
    }
    
    @ApiOperation(value = "修改密码接口")
    @ApiImplicitParam(name = "Authorization", value = "登录获取的token", required = true, dataType = "string", paramType = "header")
    @PostMapping("/updatePswd")
    public RespJson<Boolean> updatePswd(@RequestBody @Validated SystemUserVo param) throws AlertException {
    	sysUserService.updatePswd(param, UserUtil.get());
        return RespJson.success(true);
    }

}
