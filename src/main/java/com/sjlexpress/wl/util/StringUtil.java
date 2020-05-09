package com.sjlexpress.wl.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

public class StringUtil {

    public static String getUUid(){
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        return uuid;
    }
    
    /**
     * @Description: 获取10位唯一code
     * @Author: linzhenhui
     * @Date: 16:22
    **/

    public static String getUCode()
    {
        Calendar c=Calendar.getInstance();
        String time=new SimpleDateFormat("yyyy-MM-ddHHmmss").format(c.getTime()).toString();
        StringBuffer s=new StringBuffer(time.substring(14, 16));
        Long sys=System.currentTimeMillis();
        s.append(sys.toString().substring(11, 13));
        Double tm=Math.random()*10000+1;
        s.append(tm.toString().substring(tm.toString().length()-6, tm.toString().length()));
        return s.toString();
    }
}
