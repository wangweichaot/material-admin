package com.sjlexpress.wl.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Map;

/**
 * HTTP 调用相关的utils
 *
 */
@Component
public class RestUtils {
    private static RestTemplate restTemplate;

    @Autowired
    public RestUtils(RestTemplate restTemplate) {
        RestUtils.restTemplate = restTemplate;
    }

    public static <T> T get(String baseUrl, Map<String, Object> params, Class<T> clazz) {
        return restTemplate.getForObject(UrlUtils.genUrl(baseUrl, params), clazz, params);
    }

    public static <T> T get(String baseUrl, Map<String, Object> params, ParameterizedTypeReference<T> typeReference) {
        return restTemplate.exchange(UrlUtils.genUrl(baseUrl, params), HttpMethod.GET,
                null, typeReference, params).getBody();
    }

    public static <T> T post(String baseUrl, Map<String, Object> params, HttpEntity httpEntity, Class<T> clazz) {
        return restTemplate.exchange(UrlUtils.genUrl(baseUrl, params), HttpMethod.POST, httpEntity, clazz, params).getBody();
    }

    public static <T> T post(String baseUrl, Map<String, Object> params, Object body, Class<T> clazz) {
        // header
        HttpHeaders headers = new HttpHeaders();
        headers.put(HttpHeaders.CONTENT_TYPE,
                Arrays.asList(MediaType.APPLICATION_JSON_UTF8_VALUE,
                        MediaType.APPLICATION_JSON_VALUE));

        return restTemplate.exchange(UrlUtils.genUrl(baseUrl, params), HttpMethod.POST, new HttpEntity<>(body, headers), clazz, params).getBody();
    }

    public static <T> T post(String baseUrl, Map<String, Object> params, HttpEntity httpEntity, ParameterizedTypeReference<T> typeReference) {
        return restTemplate.exchange(UrlUtils.genUrl(baseUrl, params), HttpMethod.POST, httpEntity, typeReference, params).getBody();
    }

    public static <T> T post(String baseUrl, Map<String, Object> params, Object body, ParameterizedTypeReference<T> typeReference) {
        // header
        HttpHeaders headers = new HttpHeaders();
        headers.put(HttpHeaders.CONTENT_TYPE,
                Arrays.asList(MediaType.APPLICATION_JSON_UTF8_VALUE,
                        MediaType.APPLICATION_JSON_VALUE));

        return restTemplate.exchange(UrlUtils.genUrl(baseUrl, params), HttpMethod.POST, new HttpEntity<>(body, headers), typeReference, params).getBody();
    }


}
