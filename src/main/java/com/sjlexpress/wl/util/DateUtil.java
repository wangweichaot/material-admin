package com.sjlexpress.wl.util;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.springframework.util.Assert;

/**
 * 日期帮助工具
 * @author andyc
 * 2017-10-25
 */
public class DateUtil {

    private static final BigDecimal dayDecimal = BigDecimal.valueOf(86400000);
    public static final String SIMPLE_TIME_YYYYMMDDHH24MMSS = "yyyyMMddHHmmss";

    /**
     * 获取年的时间戳
     * @return
     * @throws Exception
     */
    public static Long queryTime(String time) throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = df.parse(time);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        Long timestamp = cal.getTimeInMillis();
        return timestamp;
    }
    
    /**
     * <p>Title: before</p>  
     * <p>Description: 查看两个时间谁早，null值是比较晚的时间</p>  
     * @param startDate
     * @param endDate
     * @return
     */
    public static boolean beforeAndEqual(Date startDate, Date endDate) {
    	if (startDate == null) {
    		if (endDate == null) {
    			return true;
    		}
    		return false;
    	} else {
    		if (endDate == null) {
    			return true;
    		}
    		long l = startDate.getTime()-endDate.getTime();
    		if (l <= 0) {
    			return true;
    		}
    		return false;
    	}
    }
    
    /**
     * <p>Title: after</p>  
     * <p>Description: 查看两个时间谁晚,null值是比较早的时间</p>  
     * @param startDate
     * @param endDate
     * @return
     */
    public static boolean afterAndEqual(Date startDate, Date endDate) {
    	if (startDate == null) {
    		if (endDate == null) {
    			return true;
    		}
    		return false;
    	} else {
    		if (endDate == null) {
    			return true;
    		}
    		long l = startDate.getTime()-endDate.getTime();
    		if (l >= 0) {
    			return true;
    		}
    		return false;
    	}
     }

    /**
     * 获取两个时间差的天数
     * @return
     * @throws Exception
     */
    public static Long getDayBetweenTwoDate(Date startDate, Date endDate) throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        startDate = df.parse(df.format(startDate));
        endDate = df.parse(df.format(endDate));
        long day = (endDate.getTime() - startDate.getTime()) / (24 * 60 * 60 * 1000);
        return day;
    }

    /**
     * 格式化時間 yyyy-MM-dd HH:mm:ss
     * 
     * @param date
     * @return
     */
    public static String formatDateToString(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = "";
        if (date != null) {
            dateStr = format.format(date);
        }
        return dateStr;
    }
    
    /**
     * 格式化時間 yyyy-MM-dd HH:mm:ss
     * 
     * @param date
     * @return
     */
    public static String formatDateToString2(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = "";
        if (date != null) {
            dateStr = format.format(date);
        }
        return dateStr;
    }

    /**
     * 把字符串转化成日期
     * @param date
     * @param format
     * @return
     */
    public static Date parseStringToDate(String date, String format) {
        if (StringUtilsWL.isBlank(date)) { return null; }
        if (StringUtilsWL.isBlank(format)) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static Date parseStringToDate(String date) {
    	return parseStringToDate(date, "yyyy-MM-dd HH:mm:ss");
    }

    public static String formatDateToString(Date date, String formatts) {
        SimpleDateFormat format = new SimpleDateFormat(formatts);
        String dateStr = "";
        if (date != null) {
            dateStr = format.format(date);
        }
        return dateStr;
    }

    public static Date getLastDate(Date date, BigDecimal signExtendTime, String signNormTime) throws ParseException {
        DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String deliveryDate = sdf.format(date);
        sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();

        Date newDate = sdf.parse(deliveryDate + " " + signNormTime);
        if (date.after(newDate)) {
            //签收时间在标准时间后则推迟到下一天
            calendar.setTime(newDate);
            calendar.add(Calendar.HOUR, +24);
            newDate = calendar.getTime();
        }
        if (signExtendTime != null) {
            calendar.setTime(newDate);
            calendar.add(Calendar.MILLISECOND, signExtendTime.multiply(dayDecimal).intValue());
            return calendar.getTime();
        }
        return newDate;
    }

    private static Date defaultDate = new Date();

    /**
     * 获取当前日期
     */
    public static Date getDefaultDate() {
        DateUtil.defaultDate.setTime(System.currentTimeMillis());
        return DateUtil.defaultDate;
    }

    /**
     * 获取过去的分钟
     * @param date
     * @return
     */
    public static long pastMinutes(Date date) {
        final long t = DateUtil.getDefaultDate().getTime() - date.getTime();
        return t / (60 * 1000);
    }

    /**获取现在至第二天还有多长时间(秒)*/
    public static long getSurplusSecond() {
        ZoneId zoneId = TimeZone.getTimeZone("GMT+8").toZoneId();
        LocalDate date = LocalDate.now(zoneId).plusDays(1);
        LocalDateTime dateTime = LocalDateTime.now(zoneId);
        long seconds = dateTime.until(date.atStartOfDay(), ChronoUnit.SECONDS);
        return seconds;
    }

    /**
     * 格式 yyyyMMdd.
     * @return yyyyMMdd
     */
    public static String getDateDay() {
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(getDefaultDate());
    }

    public static String getDateTimeStr() {
        return formatDateToString(getDefaultDate());
    }

    public static double getDistanceDate(Date before, Date after) {
        Assert.notNull(before, "The param before must not be null!");
        Assert.notNull(after, "The param after must not be null!");
        long beforeTime = before.getTime();
        long afterTime = after.getTime();
        return (afterTime - beforeTime) / (1000 * 60 * 60 * 24);
    }

    /**
     * 返回两个日期之间间隔的小时数<br>
     * 不足一小时以一小时计<br>
     * 如: before 为 2018-03-05 20:13:23,<br>
     * after 为 2018-03-05 21:14:23,<br>
     * 则返回的小时差值为 2
     */
    public static int getDistanceHours(Date before, Date after) {
        Assert.notNull(before, "The param before must not be null!");
        Assert.notNull(after, "The param after must not be null!");
        long beforeTime = before.getTime();
        long afterTime = after.getTime();
        long times = afterTime - beforeTime;
        long mods = times % (1000 * 60 * 60);
        int hours = (int) (times / (1000 * 60 * 60));
        if (mods > 0) hours = hours + 1;
        return hours;
    }

    /**
     * 返回两个日期之间间隔的分钟数<br>不足一分钟以一分钟计
     */
    public static int getDistanceMin(Date before, Date after) {
        Assert.notNull(before, "The param before must not be null!");
        Assert.notNull(after, "The param after must not be null!");
        long beforeTime = before.getTime();
        long afterTime = after.getTime();
        long times = afterTime - beforeTime;
        long mods = times % (1000 * 60);
        int minutes = (int) (times / (1000 * 60));
        if (mods > 0) minutes = minutes + 1;
        return minutes;
    }
    
    /**
     * @param str
     * @return
     * @函数名称：stringToUtilDate
     * @功能描述：将String型的日期格式转换为Util型的日期格式
     */
    public static java.util.Date stringToUtilDate(String str, String format) {
        if (null != str && str.length() > 0) {
            try {
                return (new SimpleDateFormat(format)).parse(str);
            } catch (ParseException ex) {
                ex.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }
   
}
