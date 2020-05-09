package com.sjlexpress.wl.constant;

/**
 * 时间相关常量数据
 *
 * @author zhicheng.lai
 */
public class TimeConstants {

    /**
     * 登录过期时间 30分钟
     */
    public final static long LOGIN_EXPIRE_TIMESTAMP = 30 * 60 * 1000L;

    /**
     * 一天时间戳
     */
    public final static long ONE_DAYS_MILLISECOND = 24 * 60 * 60 * 1000L;

    /**
     * 包裹存放超时时间 140天
     */
    public final static long PARCEL_TIME_OUT_DAYS = 140;

    /**
     * 包裹存放超时时间 140天
     */
    public final static long PARCEL_TIME_OUT = PARCEL_TIME_OUT_DAYS * ONE_DAYS_MILLISECOND;

}
