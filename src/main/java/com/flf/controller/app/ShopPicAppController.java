package com.flf.controller.app;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.flf.entity.ShopPic;
import com.flf.service.ShopPicService;
import com.flf.util.JSONUtils;


/**
 * @author Arthur
 *
 */
@Controller
@RequestMapping(value="/shopPic")
public class ShopPicAppController {

	private final static Logger log = Logger.getLogger(ShopPicAppController.class);
	
	@Autowired(required = false)
	private ShopPicService service;
	
	/**
	 * @param id
	 * @param response
	 * 获取一元抢购图片
	 */
	@RequestMapping(value = "/getPic")
	public void getStock(HttpServletResponse response){
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		try {
			ShopPic p = service.queryById(1);
			String url = p.getPicUrl();
			jsonMap.put("pic", url);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				JSONUtils.printObject(jsonMap, response);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	@RequestMapping(value = "/getShow")
	public void getShow(HttpServletResponse response){
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		try {
			String url = "http://120.77.81.97:88/images/show.png";
			jsonMap.put("pic", url);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				JSONUtils.printObject(jsonMap, response);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
}
