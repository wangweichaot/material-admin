package com.sjlexpress.wl.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 功能描述: 时间工具类
 *
 * @author zhicheng.lai
 */
public class TimeUtil {
    /**
     * 转换某个时间为字符串
     *
     * @param str  :  需要转换的时间格式，例如 yyyy-MM-dd HH:mm:ss SSS
     * @param date : 需要转换的时间
     * @return :java.lang.String
     * @throws Exception :
     * @author zhicheng.lai
     * @date : 2018/12/5 0005 10:53
     */
    public static String getString(String str, Date date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(str);
            return sdf.format(date);
        } catch (Exception e) {
            return null;
        }
    }

    //获取当前时间的10位时间戳
    public static Integer getTimestamps10() {
        return (int) (System.currentTimeMillis() / 1000);
    }

    /**
     * 将某个字符串的时间修改为10位时间戳
     *
     * @param str  : 字符串时间的格式 PS： yyyy-MM-dd HH:mm:ss SSS
     * @param date :	字符串时间	PS： 2018-12-05 10:59:59 152
     * @return :java.lang.Integer
     * @throws Exception :
     * @author zhicheng.lai
     * @date : 2018/12/5 0005 10:57
     */
    public static Integer getTimestamps10(String str, String date) {
        SimpleDateFormat sdf = new SimpleDateFormat(str);
        try {
            return (int) (sdf.parse(date).getTime() / 1000);
        } catch (Exception e) {
        }
        return getTimestamps10();
    }

    /**
     * 获取当前时间的字符串
     *
     * @param str :	字符串格式	PS： yyyy-MM-dd HH:mm:ss SSS
     * @return :java.lang.String		PS： 2018-12-05 10:59:59 152
     * @throws Exception :
     * @author zhicheng.lai
     * @date : 2018/12/5 0005 10:59
     */
    public static String getString(String str) {
        return getString(str, new Date());
    }

    /**
     * 获取按时间开头和随机数结尾的字符串。例如订单号。
     *
     * @param num :	随机数长度。PS 输入9000，则获得1000~9999
     * @return :java.lang.String
     * @throws Exception :
     * @author zhicheng.lai
     * @date : 2018/12/5 0005 11:00
     */
    public static String getOrderId(Integer num) {
        if (num == null || num == 0) {
            num = 9000;
        }
        return getString("yyyyMMddHHmmss", new Date()) + (int) (Math.random() * num + num / 9);
    }

    /**
     * 获取某个时间前几天或者后几天
     *
     * @param today    : 时间
     * @param interval : 需要增减的天数
     * @return :java.util.Date
     * @throws Exception :
     * @author zhicheng.lai
     * @date : 2018/12/5 0005 19:39
     */
    public static Date dateAdd(Date today, Integer interval) {
        if (today == null) {
            today = new Date();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(today);
        c.add(Calendar.DAY_OF_MONTH, interval);// 今天+n天
        Date tomorrow = c.getTime();
        return tomorrow;
    }


    public static void main(String[] args) {


    }


}
