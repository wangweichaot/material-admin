package com.sjlexpress.wl.util;

import com.sjlexpress.wl.constant.HttpConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 链接处理工具类
 */
@Slf4j
public class UrlUtils {

    private final static String HTTPS = "https:";

    public static String getHttpUrl(String url, String prefix) {
        if (StringUtils.isBlank(url)) {
            return null;
        }
        if (url.startsWith(HttpConstants.SCHEMA_HTTP) || url.startsWith(HttpConstants.SCHEMA_HTTPS)) {
            return url;
        }
        return prefix + url;
    }

    public static String getHttpUrl(String url) {
        return getHttpUrl(url, HTTPS);
    }


    /**
     * 生成url: http://xxx.com?a={a}&b={b}
     *
     * @param baseUrl 基础链接
     * @param params  参数
     * @return 带占位符的url
     */
    static String genUrl(String baseUrl, Map<String, Object> params) {
        StringBuilder builder = base(baseUrl, params);
        if (CollectionUtils.isEmpty(params)) {
            return builder.toString();
        }

        List listParams;
        Map<String, Object> listMap = new HashMap<>(16, 1f);
        // 拼接占位符
        Object value;
        for (String key : params.keySet()) {
            if (StringUtils.isBlank(key)) {
                continue;
            }

            value = params.get(key);
            if (value instanceof List) {
                listParams = (List) value;
                for (int i = 0; i < listParams.size(); i++) {
                    final String listParamKey = key + "_" + i;
                    listMap.put(listParamKey, listParams.get(i));
                    builder.append(key).append("={").append(listParamKey).append("}").append(HttpConstants.Symbol.AMPERSAND);
                }
            } else {
                builder.append(key).append("={").append(key).append("}").append(HttpConstants.Symbol.AMPERSAND);
            }
        }
        params.putAll(listMap);
        return builder.deleteCharAt(builder.length() - 1).toString();
    }

    private static StringBuilder base(String baseUrl, Map<String, Object> params) {
        StringBuilder builder = new StringBuilder(baseUrl);

        // uri为空
        if (StringUtils.isBlank(baseUrl)) {
            return builder;
        }

        // 参数为空
        if (params == null || params.isEmpty()) {
            return builder;
        }

        // 基础链接中是否已经带有?
        if (StringUtils.contains(baseUrl, HttpConstants.Symbol.QUESTION_MARK)) {
            builder.append(HttpConstants.Symbol.AMPERSAND);
        } else {
            builder.append(HttpConstants.Symbol.QUESTION_MARK);
        }
        return builder;
    }

    /**
     * 生成url: http://xxx.com?a={a}&b={b}
     *
     * @param baseUrl 基础链接
     * @param params  参数
     * @return 带占位符的url
     */
    public static String genUrlWithValue(String baseUrl, Map<String, Object> params) {
        StringBuilder builder = base(baseUrl, params);
        if (CollectionUtils.isEmpty(params)) {
            return builder.toString();
        }
        // 拼接占位符
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            if (StringUtils.isBlank(entry.getKey())) {
                continue;
            }
            builder.append(entry.getKey()).append("=").append(entry.getValue()).append(HttpConstants.Symbol.AMPERSAND);
        }

        return builder.deleteCharAt(builder.length() - 1).toString();
    }
}
