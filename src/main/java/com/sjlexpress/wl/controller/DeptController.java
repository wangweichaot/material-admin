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

import com.sjlexpress.wl.dto.DeptDto;
import com.sjlexpress.wl.exception.AlertException;
import com.sjlexpress.wl.param.DeptParam;
import com.sjlexpress.wl.pojo.Dept;
import com.sjlexpress.wl.service.IDeptService;
import com.sjlexpress.wl.util.UserUtil;
import com.sjlexpress.wl.vo.DeptUpdateVo;
import com.sjlexpress.wl.vo.DeptVo;
import com.sjlexpress.wl.vo.PageVo;
import com.sjlexpress.wl.vo.RespJson;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 功能描述: 部门/仓库管理
 * @author wangweichao
 * @date 2020-5-7
 */
@Api(value = "/dept", tags = "部门/仓库管理")
@RestController
@RequestMapping("/dept")
@CrossOrigin
public class DeptController {

    @Autowired
    private IDeptService deptService;

    @ApiOperation(value = "分页查询部门/仓库列表")
    @ApiImplicitParam(name = "Authorization", value = "登录获取的token", required = true, dataType = "string", paramType = "header")
    @GetMapping("/getDeptList")
    public RespJson<PageVo<DeptDto>> getDeptList(DeptParam param) throws AlertException {
        PageVo<DeptDto> list = deptService.getDeptList(UserUtil.get(), param);
        return RespJson.success(list);
    }
    
    @ApiOperation(value = "查询列表(不分页，下拉框使用)")
    @ApiImplicitParam(name = "Authorization", value = "登录获取的token", required = true, dataType = "string", paramType = "header")
    @GetMapping("/getDeptListNoPage")
    public RespJson<List<Dept>> getDeptListNoPage() throws AlertException {
        List<Dept> list = deptService.getDeptListNoPage();
        return RespJson.success(list);
    }
    
    @ApiOperation(value = "新增部门/仓库")
    @ApiImplicitParam(name = "Authorization", value = "登录获取的token", required = true, dataType = "string", paramType = "header")
    @PostMapping("/addDept")
    public RespJson<Boolean> addDept(@RequestBody @Validated DeptVo param) throws AlertException {
    	deptService.addDept(param, UserUtil.get());
        return RespJson.success(true);
    }
    
    @ApiOperation(value = "修改部门/仓库")
    @ApiImplicitParam(name = "Authorization", value = "登录获取的token", required = true, dataType = "string", paramType = "header")
    @PostMapping("/updateDept")
    public RespJson<Boolean> updateDept(@RequestBody @Validated DeptVo param) throws AlertException {
    	deptService.updateDept(param, UserUtil.get());
        return RespJson.success(true);
    }
    
    @ApiOperation(value = "查询部门/仓库详情")
    @ApiImplicitParam(name = "Authorization", value = "登录获取的token", required = true, dataType = "string", paramType = "header")
    @GetMapping("/getDeptDetail")
    public RespJson<DeptVo> getDeptDetail(@RequestParam String id) throws AlertException {
    	DeptVo detail = deptService.getDeptDetail(UserUtil.get(), id);
        return RespJson.success(detail);
    }
    
    @ApiOperation(value = "部门/仓库启用/禁用接口")
    @ApiImplicitParam(name = "Authorization", value = "登录获取的token", required = true, dataType = "string", paramType = "header")
    @PostMapping("/openCloseDept")
    public RespJson<Boolean> openCloseDept(@RequestBody @Validated DeptUpdateVo param) throws AlertException {
    	deptService.openCloseDept(param, UserUtil.get());
        return RespJson.success(true);
    }
    

}
