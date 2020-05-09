package com.sjlexpress.wl.interceptor;

import com.sjlexpress.wl.exception.AlertException;
import com.sjlexpress.wl.exception.LoginException;
import com.sjlexpress.wl.vo.RespCode;
import com.sjlexpress.wl.vo.RespJson;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.net.BindException;
import java.util.stream.Collectors;


/**
 * 
 * @author: linzhenhui
 * @date: 2020年3月29日下午7:23:07
 * @desc:
 */
@ControllerAdvice
@Log4j2
public class ExceptionAspect {

    /**
     * 处理自定义错误返回信息异常
     */
    @ExceptionHandler(AlertException.class)
    @ResponseBody
    RespJson alertException(AlertException e) {
        return RespJson.fail(RespCode.ALERT, e.getMessage());
    }

    /**
     * 处理登录失效异常
     */
    @ExceptionHandler(LoginException.class)
    @ResponseBody
    RespJson loginException() {
        return RespJson.fail(RespCode.LOGINOUT, RespCode.LOGINOUT.getMsg());
    }

    /**
     * 处理请求类型错误异常
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    RespJson httpRequestMethodNotSupportedException(Exception e) {
        return RespJson.fail(RespCode.ERROR, "不支持该请求类型：" + e.getMessage());
    }

    /**
     * 处理登录失效异常
     */
    @ExceptionHandler(ServletRequestBindingException.class)
    @ResponseBody
    RespJson ervletRequestBindingException(Exception e) {
        return RespJson.fail(RespCode.ERROR, "请求参数丢失：" + e.getMessage());
    }


    /**
     *  处理Get请求中 使用@Valid 验证路径中请求实体校验失败后抛出的异常，详情继续往下看代码
     */
    @ExceptionHandler(BindException.class)
    @ResponseBody
    RespJson BindExceptionHandler(BindException e) {
        String message = e.getMessage();
        return RespJson.fail(RespCode.ALERT, message);
    }

    /**
     * 处理请求参数格式错误 @RequestParam上validate失败后抛出的异常是javax.validation.ConstraintViolationException
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    RespJson ConstraintViolationExceptionHandler(ConstraintViolationException e) {
        String message = e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining());
        return RespJson.fail(RespCode.ALERT, message);
    }

    /**
     *   处理请求参数格式错误 @RequestBody上validate失败后抛出的异常是MethodArgumentNotValidException异常。
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    RespJson MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining());
        return RespJson.fail(RespCode.ALERT, message);
    }

    /**
     * 处理严重错误
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    RespJson exception(Exception e) {
        log.error("严重错误：[{}]", e.getMessage(), e);
        return RespJson.fail(RespCode.ERROR, e.getMessage());
    }

}
