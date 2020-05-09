package com.sjlexpress.wl.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * @ClassName AdminInitConfig
 * @Description: 超级管理员初始化 映射数据
 * @Author wangweichao
 * @Date 2020/04/26
 * @Version V1.0
 **/
@Component
@ConfigurationProperties(prefix = "admininit.config")
@Data
public class AdminInitConfig {
	
	/**
	 * 登录名
	 */
    private String userAccount;
    
    /**
	 * 登录密码
	 */
    private String pswd;
   
}
