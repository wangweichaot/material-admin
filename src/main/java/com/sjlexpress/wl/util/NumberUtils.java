package com.sjlexpress.wl.util;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 数值有关的工具类
 * @author earl
 */
public class NumberUtils {

    /**
     * Double保留两位小数.
     * @param num Double
     * @return Double
     */
    public static double getRound(Double num) {
        Double temp = 0.00d;
        if (num != null) {
            temp = num * 100;
            temp = new BigDecimal(num).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        }

        return temp;
    }

    /**
     * 如果{@code number}含有小数，且超过两位，将对number小数部分四舍五入后保留两位小数
     *
     * <pre>
     * NumberUtil.getFloatRound(null)  = 0.0f
     * NumberUtil.getFloatRound(1)  = 1.0f
     * NumberUtil.getFloatRound(1.33)  = 1.33f
     * NumberUtil.getFloatRound(1.333)  = 1.33f
     * NumberUtil.getFloatRound(1.338)  = 1.34f
     * </pre>
     */
    public static float getFloatRound(Number number) {
        float result = 0.0f;
        if (number != null) {
            result = new BigDecimal(number.doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
        }
        return result;
    }

    /**
     * @return the value {@code 0} if {@code number1 == number2};<br>
     *         a value less than {@code 0} if {@code number1 < number2}; <br>
     *         a value greater than {@code 0} if {@code number1 > number2}
     */
    public static int compare(Number number1, Number number2) {
        if ((number1 == null) && (number2 == null)) { return 0; }
        if (number1 == null) { return -1; }
        if (number2 == null) { return 1; }
        final long n1 = number1.longValue();
        final long n2 = number2.longValue();
        return (n1 < n2) ? -1 : ((n1 == n2) ? 0 : 1);
    }

    /**
     * <pre>
     * NumberUtil.isEqual(null, *)  = false
     * NumberUtil.isEqual(*, null)  = false
     * NumberUtil.isEqual(1, 1.0f)  = true
     * </pre>
     */
    public static boolean isEqual(Number number1, Number number2) {
        if ((number1 == null) && (number2 == null)) { return true; }
        if ((number1 == null) || (number2 == null)) { return false; }
        return (number1.longValue() == number2.longValue());
    }

    /**
     * <pre>
     * NumberUtil.isNotEqual(null, *)  = true
     * NumberUtil.isNotEqual(*, null)  = true
     * NumberUtil.isNotEqual(1, 1.0f)  = false
     * </pre>
     */
    public static boolean isNotEqual(Number number1, Number number2) {
        if ((number1 == null) && (number2 == null)) { return false; }
        if ((number1 == null) || (number2 == null)) { return true; }
        return (number1.longValue() != number2.longValue());
    }

    /**
     * <pre>
     * NumberUtil.isBetween(null, *, *)  = false
     * NumberUtil.isBetween(*, null, null)  = false
     * NumberUtil.isBetween(1, 2, 3)  = false
     * NumberUtil.isBetween(2, 2, 3)  = true
     * NumberUtil.isBetween(2, 1, 3)  = true
     * NumberUtil.isBetween(3, 2, 3)  = true
     * NumberUtil.isBetween(3, 1, 3)  = true
     * </pre>
     */
    public static boolean isBetween(Number number, Number min, Number max) {
        if ((number == null) || (min == null) || (max == null)) { return false; }
        return ((number.longValue() >= min.longValue()) && (number.longValue() <= max.longValue()));
    }

    /**
     * <pre>
     * NumberUtil.isNotBetween(null, *, *)  = true
     * NumberUtil.isNotBetween(*, null, null)  = true
     * NumberUtil.isNotBetween(*, *, null)  = true
     * NumberUtil.isNotBetween(*, null, *)  = true
     * NumberUtil.isNotBetween(1, 2, 3)  = true
     * NumberUtil.isNotBetween(2, 2, 3)  = false
     * NumberUtil.isNotBetween(2, 1, 3)  = false
     * NumberUtil.isNotBetween(3, 2, 3)  = false
     * NumberUtil.isNotBetween(3, 1, 3)  = false
     * </pre>
     */
    public static boolean isNotBetween(Number number, Number min, Number max) {
        return !NumberUtils.isBetween(number, min, max);
    }

    /**
     * <pre>
     * NumberUtil.isNullNumber(null)  = true
     * NumberUtil.isNullNumber(*)  = false
     * </pre>
     */
    public static boolean isNullNumber(Number number) {
        return number == null ? true : false;
    }

    /**
     * <pre>
     * NumberUtil.isNullNumber(null)  = true
     * NumberUtil.isNullNumber(0)  = true
     * NumberUtil.isNullNumber(*)  = false
     * </pre>
     */
    public static boolean isNullOrZeroNumber(Number number) {
        return (number == null) || (number.longValue() == 0);
    }

    /**
     * <pre>
     * NumberUtil.isLeZeroNumber(null)  = true
     * NumberUtil.isLeZeroNumber(0)  = true
     * NumberUtil.isLeZeroNumber(-1)  = true
     * NumberUtil.isLeZeroNumber(1)  = false
     * </pre>
     */
    public static boolean isLeZeroNumber(Number number) {
        return (number == null) || (number.longValue() <= 0);
    }

    /**
     * <pre>
     * NumberUtil.isLtZeroNumber(null)  = true
     * NumberUtil.isLtZeroNumber(-1)  = true
     * NumberUtil.isLtZeroNumber(0)  = false
     * NumberUtil.isLtZeroNumber(1)  = false
     * </pre>
     */
    public static boolean isLtZeroNumber(Number number) {
        return (number == null) || (number.longValue() < 0);
    }

    /**
     * <pre>
     * NumberUtil.isNotZeroNumber(null)  = false
     * NumberUtil.isNotZeroNumber(0)  = true
     * NumberUtil.isNotZeroNumber(-1)  = false
     * NumberUtil.isNotZeroNumber(1)  = false
     * </pre>
     */
    public static boolean isNotZeroNumber(Number number) {
        return (number != null) && (number.longValue() != 0);
    }

    /**
     * <pre>
     * NumberUtil.isGeZeroNumber(null)  = false
     * NumberUtil.isGeZeroNumber(-1)  = false
     * NumberUtil.isGeZeroNumber(0)  = true
     * NumberUtil.isGeZeroNumber(1)  = true
     * </pre>
     */
    public static boolean isGeZeroNumber(Number number) {
        return (number != null) && (number.longValue() >= 0);
    }

    /**
     * <pre>
     * NumberUtil.isGeZeroNumber(null)  = false
     * NumberUtil.isGeZeroNumber(-1)  = false
     * NumberUtil.isGeZeroNumber(0)  = false
     * NumberUtil.isGeZeroNumber(1)  = true
     * </pre>
     */
    public static boolean isGtZeroNumber(Number number) {
        return (number != null) && (number.longValue() > 0);
    }

    /**
     * two float digtial to add
     */
    public static float floatAdd(float a, float b) {

        final BigDecimal b1 = new BigDecimal(a + "");
        final BigDecimal b2 = new BigDecimal(b + "");
        final float f = b1.add(b2).floatValue();
        return f;
    }

    /**
     * two double digtial to add
     */
    public static double doubleAdd(double a, double b) {

        final BigDecimal b1 = new BigDecimal(a + "");
        final BigDecimal b2 = new BigDecimal(b + "");
        final double f = b1.add(b2).doubleValue();
        return f;
    }

    public static double getDoubleValue(Number a, double defaultValue) {
        if (a == null) { return defaultValue; }
        return a.doubleValue();
    }

    public static int getIntegerValue(Number number, int defalutVallue) {
        if (number == null) { return defalutVallue; }
        return number.intValue();
    }

    /**
     * <pre>
     *   NumberUtils.toInt(null) = 0
     *   NumberUtils.toInt("")   = 0
     *   NumberUtils.toInt("1")  = 1
     * </pre>
     */
    public static int toInt(final String str) {
        return toInt(str, 0);
    }

    /**
     * <pre>
     *   NumberUtils.toInt(null, 1) = 1
     *   NumberUtils.toInt("", 1)   = 1
     *   NumberUtils.toInt("1", 0)  = 1
     * </pre>
     */
    public static int toInt(final String str, final int defaultValue) {
        if (str == null) { return defaultValue; }
        try {
            return Integer.parseInt(str);
        } catch (final NumberFormatException nfe) {
            return defaultValue;
        }
    }

    /**
     * <pre>
     *   NumberUtils.toInteger(null, 1)  = 1
     *   NumberUtils.toInteger("", 1)    = 1
     *   NumberUtils.toInteger("1", 0)   = 1
     *   NumberUtils.toInteger("", null) = null
     * </pre>
     */
    public static Integer toInteger(final String str, final Integer defaultValue) {
        if (str == null) { return defaultValue; }
        try {
            return Integer.parseInt(str);
        } catch (final NumberFormatException nfe) {
            return defaultValue;
        }
    }

    public static BigDecimal defaultIfNull(BigDecimal number, BigDecimal defaultValue) {
        if (number == null) return defaultValue;
        return number;
    }

    public static BigDecimal subtractIfNotNull(BigDecimal number, BigDecimal subtractNumber, BigDecimal defaultValue) {
        if (number == null) return defaultValue;
        return number.subtract(subtractNumber);
    }

    public static boolean isEqualBigDecimal(BigDecimal a, BigDecimal b) {
        if (a == null || b == null) return false;
        if (a.compareTo(b) == 0) return true;
        return false;
    }

    public static BigDecimal addBigDecimal(BigDecimal... numbers) {
        BigDecimal result = BigDecimal.ZERO;
        BigDecimal defaultValue = BigDecimal.ZERO;
        for (BigDecimal number : numbers) {
            result = result.add(defaultIfNull(number, defaultValue));
        }
        return result;
    }

    public static BigDecimal multiplyBigDecimal(BigDecimal number) {
        if (number == null) return BigDecimal.ZERO;
        return number.negate();
    }
    
    /**
     * 匹配是否为数字
     * @param str
     * @return
     * zk
     */
    public static boolean strIsNumber(String str) {
        // 该正则表达式可以匹配所有的数字 包括负数
        Pattern pattern = Pattern.compile("-?[0-9]+(\\.[0-9]+)?");
        String bigStr;
        try {
            bigStr = new BigDecimal(str).toString();
        } catch (Exception e) {
            return false;//异常 说明包含非数字。
        }
        Matcher isNum = pattern.matcher(bigStr); // matcher是全匹配
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }
    /**
     * 获取字符串转换成小数后的小数位
     * 小数位大于参数 true，否则false
     * @param decimal
     * @return
     * zk
     */
    public static Boolean getDecimalDigits(String decimal,int digit) {
    	Boolean lean = false;
    	if (StringUtilsWL.isNotBlank(decimal)) {
    		if (strIsNumber(decimal)) {
    			BigDecimal bigValue = new BigDecimal(decimal);
    			int scale = bigValue.scale();
    			if (scale > digit) {
    				lean = true;
    			}
    		}else {
    			lean = true;
    		}
    	}
    	return lean;
    }
}
