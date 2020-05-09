package com.sjlexpress.wl.param;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author lin
 */
@ApiModel(value = "com.sjlexpress.wl.param.LoginParam", description = "登录请求参数对象")
@Data
public class LoginParam {
    @ApiModelProperty(name = "登录名称", value = "登录名称", required = true)
    @NotBlank(message = "登录名不可为空")
    private String userAccount;
    @ApiModelProperty(name = "密码", value = "密码", required = true)
    @JSONField(name = "password")
    private String password;
    @ApiModelProperty(name = "code", value = "验证码", required = true)
    private String code;

}
