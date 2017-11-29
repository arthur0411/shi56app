package com.weixin;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.flf.util.HttpClientUtils;

public class JSSDKUtil {
	
	private static final Logger log = Logger.getLogger(JSSDKUtil.class);

	private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token";
	
	private static final String GET_TICKET_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket";
	public static String getAccessToken() {
		Map<String,String> map = new HashMap<String,String>();
		map.put("grant_type","client_credential");
		map.put("appid", Configure.getAppid());
		map.put("secret", Configure.getKey());
		String returnStr = null;
		try {
			returnStr = HttpClientUtils.simpleGetInvoke(ACCESS_TOKEN_URL,map);
			log.info("获取access_token的返回值为"+returnStr);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return returnStr;
	}
	public static String getTicket() {
		String returnStr = null;
		String str =  getAccessToken();
		JSONObject dataJson=new JSONObject(str);
		String accessToken = dataJson.optString("access_token");
		Map<String,String> map = new HashMap<String,String>();
		map.put("access_token", accessToken);
		map.put("type", "jsapi");
		try {
			returnStr = HttpClientUtils.simpleGetInvoke(GET_TICKET_URL, map);
			log.info("获取ticket的返回值为"+returnStr);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		JSONObject dj =new JSONObject(returnStr);
		String err = dj.optString("errcode");
		String ticket ="";
		if(err.equals("0")) {
			ticket = dj.optString("ticket");
		}
		return ticket;
	}
	
	public static void main(String[] args) {
		JSONObject dataJson=new JSONObject("{\"access_token\":\"w7r-2GzwvDdqiyZ-k0sUiYsu7EanbF3h0XdvAh10saSpminfOqyzb7IXzgddfr9_yqjj6iJ6C7t8igpgpMnGukpybdgZFQHLVm9h-3ZMKmwKTRaAGAQZX\",\"expires_in\":7200}");
		String accessToken = dataJson.optString("access_token");
		System.out.println(accessToken);
	}
	
}
