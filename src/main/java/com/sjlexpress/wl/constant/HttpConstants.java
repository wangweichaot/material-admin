package com.sjlexpress.wl.constant;

import org.springframework.http.MediaType;

import java.net.InetSocketAddress;

/**
 * HTTP请求使用的常量
 *
 * @author shuoqi.liang
 */
public class HttpConstants {

    /**
     * http
     */
    public static final String SCHEMA_HTTP = "http";

    /**
     * https
     */
    public static final String SCHEMA_HTTPS = "https";

    /**
     * GZIP
     */
    public static final String GZIP = "gzip";

    /**
     * 请求超时时间, 单位毫秒
     */
    public static final int CONNECTION_TIMEOUT = 1500;

    /**
     * http 获取数据超时时间, 单位毫秒
     */
    public static final int SOCKET_TIMEOUT = 1500;

    /**
     * 等待超时时间
     */
    public static final int AWAIT_TIMEOUT = 1500;

    /**
     * 从连接池中获取资源的超时时间, 如果未使用连接池, 此参数无效, 单位毫秒
     */
    public static final int CONNECTION_REQUEST_TIMEOUT = 500;

    /**
     * 最大连接数
     */
    public static final int MAX_TOTAL = 1280;

    /**
     * 路由(域名)最大同时请求量
     */
    public static final int DEFAULT_MAX_PER_ROUTE = 128;

    /**
     * async http 请求超时时间, 单位毫秒
     */
    public static final int ASYNC_CONNECTION_TIMEOUT = 1500;

    /**
     * async http 获取数据超时时间, 单位毫秒
     */
    public static final int ASYNC_SOCKET_TIMEOUT = 1500;

    /**
     * async http 总超时时间
     */
    public static final int ASYNC_TIMEOUT = 3000;

    /**
     * async http reactor 线程数量, 按每个单核处理4个来算
     */
    public static final int ASYNC_IO_THREAD_COUNT = Runtime.getRuntime().availableProcessors() << 1;

    /**
     * media type
     */
    public static class MediaTypes {
        public static final MediaType TEXT_JAVASCRIPT_UTF_8 = MediaType.valueOf("text/javascript;charset=UTF-8");
    }

    /**
     * 符号
     */
    public static class Symbol {
        /**
         * ? 问号
         */
        public static final String QUESTION_MARK = "?";

        /**
         * & 符号
         */
        public static final String AMPERSAND = "&";
    }

    /**
     * 默认编码
     */
    public static final String DEFAULT_ENCODING = "UTF-8";
}
