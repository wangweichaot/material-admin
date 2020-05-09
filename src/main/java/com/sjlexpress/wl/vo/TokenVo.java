package com.sjlexpress.wl.vo;

import java.io.Serializable;
import java.text.SimpleDateFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * token信息类
 *
 * @author lin
 */
@ApiModel(value = "com.sjlexpress.wl.vo.TokenVo", description = "token信息类，在请求头传递登录获取的token即可")
@Data
@NoArgsConstructor
public class
TokenVo implements Serializable {
    @ApiModelProperty(value = "用户的ID", hidden = true)
    private String id;
    @ApiModelProperty(value = "登录时间", hidden = true)
    private String loginTime;
    @ApiModelProperty(value = "最后一次请求时间", hidden = true)
    private String lastTimestamp;

    public TokenVo(String id) {
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = dateformat.format(System.currentTimeMillis());
        this.id = id;
        this.loginTime = dateStr;
        this.lastTimestamp = dateStr;
    }
}
