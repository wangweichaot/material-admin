package com.sjlexpress.wl.constant;

public class URIConstants {

    /**
     * 后面再该成可配置 做缓存
     */
    public final static String APPID = "wxf3b1b58702e03fb2";
    public final static String SECRET = "c8ad107f68a0e09732b511f7d0dc180b";

    /**
     * 微信提现URL
     */
    public final static String UNIFIED_ORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";

    /**
     * 微信商户账号
     */
    public final static String MCHID = "1558295871";
    /**
     * 商户密钥
     */
    public final static String PARTNER = "Ohb6XKk3CkNxPdqNCMP9tnN4UQUOw2fa";

    /**
     * 回调地址
     */
    public final static String NOTIFY_URL = "https://hyt.sjlexpress.com/notify/wxpay";


    public final static String WX_LOGIN_URL = "https://api.weixin.qq.com/sns/jscode2session?appid="
            + APPID
            + "&secret="
            + SECRET
            + "&js_code={code}&grant_type=authorization_code";

}
