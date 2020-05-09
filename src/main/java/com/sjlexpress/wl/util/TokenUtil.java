package com.sjlexpress.wl.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.sjlexpress.wl.vo.TokenVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * lin
 */
public class TokenUtil {

    public static String getToken(String userId) {
        Date start = new Date();
        long currentTime = System.currentTimeMillis() + 24*60* 60 * 1000;//一小时有效时间
        Date end = new Date(currentTime);
        String token = "";

        token = JWT.create().withAudience(userId).withIssuedAt(start).withExpiresAt(end)
                .sign(Algorithm.HMAC256(userId));
        return token;
    }


    public static TokenVo getTokenUser() {
        TokenVo tokenVo = new TokenVo();
        String token = getRequest().getHeader("Authorization");// 从 http 请求头中取出 token
        if (StringUtils.isEmpty(token)){
            return null;
        }
        try {
            if (StringUtils.isEmpty(JWT.decode(token).getAudience().get(0))){
                return null;
            } else {
                tokenVo.setId(JWT.decode(token).getAudience().get(0));
            }
        }catch (Exception e){
            return null;
        }
        return tokenVo;
    }

    /**
     * 获取request
     *
     * @return
     */
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        return requestAttributes == null ? null : requestAttributes.getRequest();
    }



}
