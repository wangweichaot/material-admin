package com.sjlexpress.wl.exception;

/**
 * 自定义异常 登录失效
 *
 * @author  zhicheng.lai
 */
public class LoginException extends MyException {

    private final static String MSG = "登录失效，请重新登录";
    public LoginException() {
        super(MSG);
    }
}
