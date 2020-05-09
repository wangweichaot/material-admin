package com.sjlexpress.wl.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSONObject;
import com.sjlexpress.wl.util.TokenUtil;
import com.sjlexpress.wl.util.UserUtil;
import com.sjlexpress.wl.vo.RespCode;
import com.sjlexpress.wl.vo.RespJson;
import com.sjlexpress.wl.vo.TokenVo;

import lombok.extern.log4j.Log4j2;


/**
 * 功能描述: 访问过滤器
 *
 * @author zhicheng.lai
 */
@Configuration
@Log4j2
public class WebSecurityConfig implements WebMvcConfigurer {

    @Bean
    public UserToken getUserToken() {
        return new UserToken();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //用户模块的拦截设置
        InterceptorRegistration addUserToken = registry.addInterceptor(getUserToken());
        // 排除配置
        addUserToken.excludePathPatterns("/error");
        addUserToken.excludePathPatterns("/sys_user/sysLogin");
        addUserToken.excludePathPatterns("/sys_user/sysRegister");

        addUserToken.excludePathPatterns("/webjars/bycdao-ui/**");
        addUserToken.excludePathPatterns("/v2/api-docs-ext");
        addUserToken.excludePathPatterns("/swagger-resources");
        addUserToken.excludePathPatterns("/doc.html");
        
        //测试方面的
        addUserToken.excludePathPatterns("/test/**");
        
        addUserToken.excludePathPatterns("/test.apk");

        //拦截配置
        addUserToken.addPathPatterns("/**");
        
        //超级管理员初始化接口
        addUserToken.excludePathPatterns("/sys_user/adminInit");
    }

    /**
     * 前端用户拦截器
     */
    private class UserToken extends HandlerInterceptorAdapter {
        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
                throws Exception {
//            log.debug("正在访问前端模块" + request.getServletPath());
//            String token = request.getHeader("Authorization");
            TokenVo user = TokenUtil.getTokenUser();
            if (null == user) {
                return respJson(RespJson.fail(RespCode.LOGINOUT, "请求token不存在"), response);
            } else {
                UserUtil.set(user);
            }
            return true;
        }

    }

    /**
     * 处理该请求
     */
    private boolean respJson(RespJson respJson, HttpServletResponse response) throws Exception {
        //如果ajax则返回权限不足
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.write(JSONObject.toJSONString(respJson));
        writer.flush();
        writer.close();
        return false;
    }


}