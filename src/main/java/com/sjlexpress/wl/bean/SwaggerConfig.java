package com.sjlexpress.wl.bean;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 类
 *
 * @Api(value = "user", tags = "前台用户")
 * value - url的路径值
 * tags - 如果设置这个值、value的值会被覆盖
 * description - 对api资源的描述
 * basePath - 基本路径可以不配置
 * authorizations - 高级特性认证时配置
 * @ApiOperation value - url的路径值
 * tags - 如果设置这个值、value的值会被覆盖
 * description - 对api资源的描述
 * basePath - 基本路径可以不配置
 * authorizations - 高级特性认证时配置
 * response - 返回的对象
 * code - http的状态码 默认 200
 * extensions - 扩展属性
 * <p>
 * 方法
 * @ApiOperation(value = "wxlogin", notes = "微信登录获取token")
 * value - url的路径值
 * tags - 如果设置这个值、value的值会被覆盖
 * description - 对api资源的描述
 * basePath - 基本路径可以不配置
 * authorizations - 高级特性认证时配置
 * response - 返回的对象
 * code - http的状态码 默认 200
 * extensions - 扩展属性
 * <p>
 * token
 * @ApiImplicitParam(name = "TOKEN", value = "Authorization token", required = true, dataType = "string", paramType = "header")
 * @ApiImplicitParams({@ApiImplicitParam(name = "TOKEN", value = "Authorization token", required = true, dataType = "string", paramType = "header")})
 * paramType：参数放在哪个地方
 * header–>请求参数的获取：@RequestHeader
 * query–>请求参数的获取：@RequestParam
 * path（用于restful接口）–>请求参数的获取：@PathVariable
 * body（不常用）
 * form（不常用）
 * name：参数名
 * dataType：参数类型
 * required：参数是否必须传
 * value：参数的意思
 * defaultValue：参数的默认值
 */

/**
 * 方法
 *  @ApiOperation(value = "wxlogin", notes = "微信登录获取token")
 * value - url的路径值
 * tags - 如果设置这个值、value的值会被覆盖
 * description - 对api资源的描述
 * basePath - 基本路径可以不配置
 * authorizations - 高级特性认证时配置
 * response - 返回的对象
 * code - http的状态码 默认 200
 * extensions - 扩展属性
 */

/**
 * token
 *  @ApiImplicitParam(name = "TOKEN", value = "Authorization token", required = true, dataType = "string", paramType = "header")
 *  @ApiImplicitParams({@ApiImplicitParam(name = "TOKEN", value = "Authorization token", required = true, dataType = "string", paramType = "header")})
 * paramType：参数放在哪个地方
 * header–>请求参数的获取：@RequestHeader
 * query–>请求参数的获取：@RequestParam
 * path（用于restful接口）–>请求参数的获取：@PathVariable
 * body（不常用）
 * form（不常用）
 * name：参数名
 * dataType：参数类型
 * required：参数是否必须传
 * value：参数的意思
 * defaultValue：参数的默认值
 */

/**
 * 参数
 * @ApiParam(name = "code", value = "登录code", required = true
 * name    - 属性名称
 * value - 属性值
 * defaultValue - 默认属性值
 * allowableValues - 可以不配置
 * required - 是否属性必填
 * access - 不过多描述
 * allowMultiple - 默认为false
 * hidden - 隐藏该属性
 * example - 举例子
 */

/**
 * 
 * @author: linzhenhui
 * @date: 2020年3月29日下午7:21:56
 * @desc:
 */
@Configuration
@EnableSwagger2
@Profile({"dev","uat"})
public class SwaggerConfig {
	
	@Value("${swagger.enable}")
    private boolean enableSwagger;

    @Bean
    public Docket hytApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("捷利物料系统接口文档")
                .apiInfo(apiInfo())
                .enable(enableSwagger)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.sjlexpress.wl.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public ApiInfo apiInfo() {
     return  new ApiInfoBuilder()
                .title("捷利物料系统")
                .description("捷利物料系统后台接口文档")
                .contact(new Contact("捷利物料系统后台", "https://org.modao.cc/app/1cfd33fb64b872730f90ff5e5238577b4c80e16a", "wangchao2035@163.com"))
                .version("1.0")
                .build();
    }

}

