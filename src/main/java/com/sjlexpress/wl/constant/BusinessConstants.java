package com.sjlexpress.wl.constant;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.MediaType;

/**
 * @Description:  业务常量
 * @Author: linzhenhui
 * @Date: 10:26
**/
public class BusinessConstants {

    /**
     * 默认收获人前缀
     */
    public static final String RECEIVER_NAME = "互运通";
    
    /**
     * 消息类型，1 普通通知(订单相关) 2.安全通知 3 广告通知 4 其他通知
     */
    public static final List<String> MSG_PUSH_TYPE = Arrays.asList("1", "2", "3");
    
    /**
     * 版本管理，文件上传类型限制
     */
    public static final List<String> VERSION_WHITE_LIST = Arrays.asList("apk");
    
    /**
     * Banner管理，文件上传类型限制
     */
    public static final List<String> BANNER_WHITE_LIST = Arrays.asList("bmp", "gif", "jpeg", "png", "jpg", "svg");
    
    /**
     * 文件上传-版本管理标志
     */
    public static final String MODULE_NAME_VERSION = "MODULE_NAME_VERSION";
    
    /**
     * 文件上传-Banner管理标志
     */
    public static final String MODULE_NAME_BANNER = "MODULE_NAME_BANNER";
}



