package com.sjlexpress.wl.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 返回code码定义
 *
 * @author zhicheng.lai
 */
@ApiModel(value = "com.sjlexpress.wl.vo.RespCode", description = "返回code码定义")
public enum RespCode {

    /**
     * 请求成功
     */
    SUCCESS(0, "SUCCESS"),

    /**
     * 操作失败
     */
    WARN(301, "操作失败"),

    /**
     * 完成跳转
     */
    JUMP(307, "完成跳转"),

    /**
     * 登录失效，请重新登录
     */
    LOGINOUT(401, "登录失效，请重新登录"),

    /**
     * 无该操作权限
     */
    AUTHORITY(403, "无该操作权限"),

    /**
     * 操作错误，请稍后重试
     */
    ALERT(406, "操作错误，请稍后重试"),

    /**
     * 系统错误，请稍后重试，如多次失败，请联系客服处理
     */
    ERROR(500, "系统错误，请稍后重试，如多次失败，请联系客服处理");

    /**
     * 规定0为成功；401为登录失效，需要重新登录；406为操作错误，可以直接弹窗提示错误信息；500为系统错误，请联系开发处理
     */
    @ApiModelProperty(value = "code，0为成功", required = true, example = "规定0为成功；401为登录失效，需要重新登录；406为操作错误，可以直接弹窗提示错误信息；500为系统错误，请联系开发处理")
    private int code;

    /**
     * 消息内容
     */
    @ApiModelProperty(value = "消息内容", required = true)
    private String msg;

    RespCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
