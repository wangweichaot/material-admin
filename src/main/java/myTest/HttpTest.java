package myTest;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;
import com.sjlexpress.wl.util.HttpUtils;

public class HttpTest {
	
	public static void main(String[] args) {
		
		//单个货币查询接口=====================
		
	    String host = "https://jisuhuilv.market.alicloudapi.com";
	    String path = "/exchange/single";
	    String method = "GET";
	    String appcode = "3d2755b078f048029aef58eb197133ad";
	    Map<String, String> headers = new HashMap<String, String>();
	    //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
	    headers.put("Authorization", "APPCODE " + appcode);
	    Map<String, String> querys = new HashMap<String, String>();
	    querys.put("currency", "CNY");


	    try {
	    	/**
	    	* 重要提示如下:
	    	* HttpUtils请从
	    	* https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
	    	* 下载
	    	*
	    	* 相应的依赖请参照
	    	* https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
	    	*/
	    	HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
//	    	System.out.println("========================="+response.toString());
	    	//获取response的body
	    	System.out.println("+++++++++++++++++++++++++"+EntityUtils.toString(response.getEntity()));
	    	
	    	String exchangeJson = EntityUtils.toString(response.getEntity());
	    	
	    	JSONObject obj = JSONObject.parseObject(exchangeJson);
	    	
	    	String currency = obj.getString("result");
	    	
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	}
}
