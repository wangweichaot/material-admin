package com.sjlexpress.wl.util;

import java.util.Random;

public class UnionIDUtil {

    private static UnionIDUtil instance;

    private final int threadIdBits = 4;// 线程号位数
    private final int maxRandomId = 9;// 随机数最大值

    private UnionIDUtil() {
    }

    public static UnionIDUtil getInstance() {
        if (instance == null) {
            instance = new UnionIDUtil();
        }
        return instance;
    }

    /**
     * 获取下一个ID 线程安全
     * @param sysId
     * @return
     */
    public synchronized long nextId(int sysId) {
        return splice(sysId);
    }

    /**
     * 拼接id：时间戳+线程号+随机数+系统标识
     * @param sysId
     * @return
     */
    private long splice(int sysId) {
        String strLong = getCurrentTime() + getCurrentThreadId() + getRandomNum() + sysId;
        return Long.parseLong(strLong);
    }

    /**
     * 系统时间戳
     * @return
     */
    private long getCurrentTime() {
        return System.currentTimeMillis();
    }

    /**
     * 获取当前线程号
     * @return
     */
    private String getCurrentThreadId() {
        String strCurrentThreadId = String.format("%0" + threadIdBits + "d", Thread.currentThread().getId());
        if (strCurrentThreadId.length() > threadIdBits) {
            strCurrentThreadId = strCurrentThreadId.substring(0, threadIdBits);
        }
        return strCurrentThreadId;
    }

    /**
     * 获取随机数
     * @return
     */
    private int getRandomNum() {
        return new Random().nextInt(maxRandomId);
    }

    public static void main(String[] args) {
        UnionIDUtil instance = UnionIDUtil.getInstance();
        instance.nextId(1);
        System.out.printf(String.valueOf(instance.nextId(1)));
    }

}
