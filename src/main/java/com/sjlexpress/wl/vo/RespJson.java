package com.sjlexpress.wl.vo;

import java.io.Serializable;

import com.sjlexpress.wl.util.TimeUtil;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 返回前端的类
 *
 * @author zhicheng.lai
 */
@ApiModel(value = "com.sjlexpress.wl.pojo.RespJson", description = "返回信息")
@Data
public class RespJson<T> implements Serializable {

    /**
     * value–字段说明
     * required–是否必填
     * example–举例说明
     * hidden–隐藏
     */
    @ApiModelProperty(value = "code，规定0为成功；401为登录失效，需要重新登录；406为操作错误，可以直接弹窗提示错误信息；500为系统错误，请联系开发处理", required = true)
    private Integer code;
    @ApiModelProperty(value = "消息内容", required = true)
    private String msg;
    @ApiModelProperty(value = "返回内容体", required = true)
    private T data;
    @ApiModelProperty(value = "返回时间yyyyMMddHHmmss格式", required = true)
    private String repTime = TimeUtil.getString("yyyyMMddHHmmss");

    /**
     * success
     */
    public static <T> RespJson<T> success(T data) {
        RespJson<T> responseJson = new RespJson<>();
        responseJson.code = RespCode.SUCCESS.getCode();
        responseJson.msg = RespCode.SUCCESS.getMsg();
        responseJson.data = data;
        return responseJson;
    }

    /**
     * success
     */
    public static <T> RespJson<T> success(T data,String message) {
        RespJson<T> responseJson = new RespJson<>();
        responseJson.code = RespCode.SUCCESS.getCode();
        responseJson.msg = message;
        responseJson.data = data;
        return responseJson;
    }

    /**
     * 错误信息,指定code和消息
     */
    public static RespJson fail(RespCode codeEnum, String msg) {
        RespJson responseJson = new RespJson();
        responseJson.setCode(codeEnum.getCode());
        responseJson.setMsg(msg);
        return responseJson;
    }

    /**
     * 错误信息,指定code。消息从枚举中获取
     */
    public static RespJson fail(RespCode codeEnum) {
        RespJson responseJson = new RespJson();
        responseJson.setCode(codeEnum.getCode());
        responseJson.setMsg(codeEnum.getMsg());
        return responseJson;
    }

}

