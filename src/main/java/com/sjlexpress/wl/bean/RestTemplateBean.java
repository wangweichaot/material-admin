package com.sjlexpress.wl.bean;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Configuration
public class RestTemplateBean {

    @Autowired
    RestTemplateBuilder restTemplateBuilider;

    @Bean
    public RestTemplate restTemplate() {
        //使用build()方法进行获取
        RestTemplate restTemplate = restTemplateBuilider.build();

        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        //1.需要定义一个convert转换消息的对象;
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        // Note: here we are making this converter to process any kind of response,
        // not only application/*json, which is the default behaviour
        fastJsonHttpMessageConverter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        messageConverters.add(fastJsonHttpMessageConverter);
        restTemplate.setMessageConverters(messageConverters);
        return restTemplate;
    }



}
