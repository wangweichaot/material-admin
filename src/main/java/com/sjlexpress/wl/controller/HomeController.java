package com.sjlexpress.wl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sjlexpress.wl.exception.AlertException;
import com.sjlexpress.wl.service.IHomeService;
import com.sjlexpress.wl.vo.HomeCountVo;
import com.sjlexpress.wl.vo.RespJson;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 功能描述: 物料系统系统首页控制器
 * @author wangweichao
 * @Date 2020-5-7
 */
@Api(value = "/home", tags = "网站首页")
@RestController
@RequestMapping("/home")
@CrossOrigin
public class HomeController {
    @Autowired
    private IHomeService homeService;
    
    @ApiOperation(value = "获取首页统计信息接口")
    @ApiImplicitParam(name = "Authorization", value = "登录获取的token", required = true, dataType = "string", paramType = "header")
    @GetMapping("/getHomeCountInfo")
    public RespJson<HomeCountVo> getHomeCountInfo() throws AlertException {
    	HomeCountVo detail = homeService.getHomeCountInfo();
        return RespJson.success(detail);
    }
    

}
