package com.sjlexpress.wl.util;

import com.sjlexpress.wl.vo.TokenVo;

public class UserUtil {
    private static final   ThreadLocal<TokenVo>  tokenThreadLocal = new ThreadLocal<>();

    public static void set(TokenVo user){
        tokenThreadLocal.set(user);
    }
    public static void remove(){
        tokenThreadLocal.remove();
    }
    public static TokenVo get(){
        return tokenThreadLocal.get();
    }
}
