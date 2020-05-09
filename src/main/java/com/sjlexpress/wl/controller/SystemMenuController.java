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

import com.sjlexpress.wl.dto.SystemMenuDto;
import com.sjlexpress.wl.exception.AlertException;
import com.sjlexpress.wl.param.SystemMenuParam;
import com.sjlexpress.wl.service.ISystemMenuService;
import com.sjlexpress.wl.util.UserUtil;
import com.sjlexpress.wl.vo.PageVo;
import com.sjlexpress.wl.vo.RespJson;
import com.sjlexpress.wl.vo.SystemMenuUpdateVo;
import com.sjlexpress.wl.vo.SystemMenuVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 功能描述: 菜单管理
 * @author wangweichao
 * @date 2020-5-7
 */
@Api(value = "/systemMenu", tags = "菜单管理")
@RestController
@RequestMapping("/systemMenu")
@CrossOrigin
public class SystemMenuController {

    @Autowired
    private ISystemMenuService systemMenuService;

    @ApiOperation(value = "分页查询菜单列表")
    @ApiImplicitParam(name = "Authorization", value = "登录获取的token", required = true, dataType = "string", paramType = "header")
    @GetMapping("/getSystemMenuList")
    public RespJson<PageVo<SystemMenuDto>> getSystemMenuList(SystemMenuParam param) throws AlertException {
        PageVo<SystemMenuDto> list = systemMenuService.getSystemMenuList(UserUtil.get(), param);
        return RespJson.success(list);
    }
    
    @ApiOperation(value = "查询列表(不分页，ztree树使用 )")
    @ApiImplicitParam(name = "Authorization", value = "登录获取的token", required = true, dataType = "string", paramType = "header")
    @GetMapping("/getCurrencyRateListNoPage")
    public RespJson<List<SystemMenuDto>> getSystemMenuListNoPage() throws AlertException {
    	List<SystemMenuDto> list = systemMenuService.getSystemMenuListNoPage();
        return RespJson.success(list);
    }
    
    @ApiOperation(value = "新增菜单")
    @ApiImplicitParam(name = "Authorization", value = "登录获取的token", required = true, dataType = "string", paramType = "header")
    @PostMapping("/addSystemMenu")
    public RespJson<Boolean> addSystemMenu(@RequestBody @Validated SystemMenuVo param) throws AlertException {
    	systemMenuService.addSystemMenu(param, UserUtil.get());
        return RespJson.success(true);
    }
    
    @ApiOperation(value = "修改菜单")
    @ApiImplicitParam(name = "Authorization", value = "登录获取的token", required = true, dataType = "string", paramType = "header")
    @PostMapping("/updateSystemMenu")
    public RespJson<Boolean> updateSystemMenu(@RequestBody @Validated SystemMenuVo param) throws AlertException {
    	systemMenuService.updateSystemMenu(param, UserUtil.get());
        return RespJson.success(true);
    }
    
    @ApiOperation(value = "查询菜单详情")
    @ApiImplicitParam(name = "Authorization", value = "登录获取的token", required = true, dataType = "string", paramType = "header")
    @GetMapping("/getSystemMenuDetail")
    public RespJson<SystemMenuVo> getSystemMenuDetail(@RequestParam String id) throws AlertException {
    	SystemMenuVo detail = systemMenuService.getSystemMenuDetail(UserUtil.get(), id);
        return RespJson.success(detail);
    }
    
    @ApiOperation(value = "菜单启用/禁用接口")
    @ApiImplicitParam(name = "Authorization", value = "登录获取的token", required = true, dataType = "string", paramType = "header")
    @PostMapping("/openCloseSystemMenu")
    public RespJson<Boolean> openCloseSystemMenu(@RequestBody @Validated SystemMenuUpdateVo param) throws AlertException {
    	systemMenuService.openCloseSystemMenu(param, UserUtil.get());
        return RespJson.success(true);
    }
    
    @ApiOperation(value = "删除菜单接口")
    @ApiImplicitParam(name = "Authorization", value = "登录获取的token", required = true, dataType = "string", paramType = "header")
    @GetMapping("/delSystemMenu")
    public RespJson<Boolean> delSystemMenu(@RequestParam Integer id) throws AlertException {
    	systemMenuService.delSystemMenu(id);
        return RespJson.success(true);
    }

}
