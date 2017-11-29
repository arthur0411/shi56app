package com.flf.controller.app;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.flf.entity.Ret;
import com.flf.service.RedisSpringProxy;
import com.flf.util.JSONUtils;
import com.flf.util.Sign;
import com.weixin.JSSDKUtil;

import net.sf.json.JSONObject;

/**
 * APP共享功能
 * 
 * @author SevenWong<br>
 * @date 2016年6月21日下午5:08:50
 */
@Controller
public class PromotionController {
	
	@Autowired
	private RedisSpringProxy redisSpringProxy;

	/**
	 * APP分享链接
	 * 
	 * @author SevenWong<br>
	 * @date 2016年6月30日下午5:34:02
	 * @param community
	 * @return
	 */
	@RequestMapping(value = "/promotion")
	public ModelAndView share(Integer userId) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("userId", userId);
		mv.setViewName("/mobile/share");
		return mv;
	}
	
	@RequestMapping(value = "/getAccess")
	public void getAccess(String url,HttpServletResponse httpServletResponse) {
		String jsapi_ticket = (String) redisSpringProxy.read("jsapi_ticket");
		if(jsapi_ticket == null || jsapi_ticket.equals("")) {
			jsapi_ticket = JSSDKUtil.getTicket();
			redisSpringProxy.saveEx("jsapi_ticket", 7200, jsapi_ticket);
		}
		Map<String, String> ret = Sign.sign(jsapi_ticket, url);
		JSONObject result = JSONObject.fromObject(ret);  
		try {
			JSONUtils.printObject(result, httpServletResponse);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/test")
	public ModelAndView test() {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("test");
		return mv;
	}
}
