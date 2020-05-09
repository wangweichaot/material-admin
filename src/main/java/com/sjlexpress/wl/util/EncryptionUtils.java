package com.sjlexpress.wl.util;


import com.sjlexpress.wl.constant.TimeConstants;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * 加密工具类
 *
 * @author zhicheng.lai
 */
public class EncryptionUtils {

    public static String md5(String input) {
        return DigestUtils.md5DigestAsHex(input.getBytes());
    }

    /**
     * 生成可解唯一token，如果部署多台需要再加一个ip地址,如果速度需要小于1毫秒需要使用纳秒
     *
     * @return
     */
    public static String token() {
        return new java.math.BigInteger(String.valueOf(System.currentTimeMillis())
                + String.valueOf(Thread.currentThread().getId()), 10).toString(36);
    }

    /**
     * 生成可解唯一token，如果部署多台需要再加一个ip地址
     *
     * @return
     */
    public static String token(Date date) {
        return new java.math.BigInteger(String.valueOf(date.getTime())
                + String.valueOf(Thread.currentThread().getId()), 10).toString(36);
    }

    /**
     * 判断token是否正确有效，30分钟
     *
     * @param token
     * @return
     */
    public static boolean codeVerify(String token, Date date) {
        try {
            long aLong = reverseToken(token);
            int currentThreadNum = (int) Math.pow(10, (int) Math.log10(aLong) - 12);
            long time = aLong / currentThreadNum / 1000 * 1000;
            if (date.getTime() == time) {
                return true;
            }
        } catch (NumberFormatException ignored) {
        }
        return false;
    }

    /**
     * 判断token是否正确有效，30分钟
     *
     * @param token
     * @return
     */
    public static boolean tokenVerification(String token) {
        try {
            long aLong = reverseToken(token);
            int currentThreadNum = (int) Math.pow(10, (int) Math.log10(aLong) - 12);
            long time = System.currentTimeMillis() - aLong / currentThreadNum;
            if (time > 0 && time < TimeConstants.LOGIN_EXPIRE_TIMESTAMP) {
                return true;
            }
        } catch (NumberFormatException ignored) {
        }
        return false;
    }

    public static long reverseToken(String token) throws NumberFormatException {
        if (StringUtils.isEmpty(token)) {
            return 0L;
        }
        return new java.math.BigInteger(token, 36).longValue();
    }

    public static String encryptPassword(String username, String password, String salt) {
        return DigestUtils.md5DigestAsHex((username + password + salt).getBytes());
    }

    public static void main(String[] args) {
//        Date date = new Date();
//        String token = token(date);
//        System.err.println(reverseToken("1k3o2t23ph"));
//    	System.out.println(md5("abc123456"));
    	String uUid = StringUtil.getUUid();
    	System.out.println(uUid);
    }

}
