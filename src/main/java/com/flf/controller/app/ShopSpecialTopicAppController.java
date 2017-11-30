package com.flf.controller.app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.flf.service.ShopSpecialTopicService;
import com.flf.util.JSONUtils;

/**
 * 
 * <br>
 * <b>功能：</b>ShopSpecialTopicController<br>
 * <br>
 */
@Controller
@RequestMapping(value = "/shopSpecialTopicApp")
public class ShopSpecialTopicAppController {

	private final static Logger log = Logger.getLogger(ShopSpecialTopicAppController.class);
	@Autowired(required = false)
	private ShopSpecialTopicService shopSpecialTopicService;

	/**
	 * 获取专题
	 */
	@RequestMapping(value = "/getSpecialTopicList")
	public void getSpecialTopicList(int type, HttpServletResponse response) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		List<Map<String, Object>> specialTopicList = null;
		try {
			log.info("按照专题分类 " + type + "获取专题");
			specialTopicList = shopSpecialTopicService.getSpecialTopicList(type);
			for (Map<String, Object> obj : specialTopicList) {
				List<Object> imgList = new ArrayList<Object>();
				if (null != obj.get("img1")) {
					imgList.add(obj.get("img1"));
				}
				if (null != obj.get("img2")) {
					imgList.add(obj.get("img2"));
				}
				if (null != obj.get("img3")) {
					imgList.add(obj.get("img3"));
				}
				if (null != obj.get("img4")) {
					imgList.add(obj.get("img4"));
				}
				if (null != obj.get("img5")) {
					imgList.add(obj.get("img5"));
				}
				if (null != obj.get("img6")) {
					imgList.add(obj.get("img6"));
				}
				if (null != obj.get("img7")) {
					imgList.add(obj.get("img7"));
				}
				obj.put("imgList", imgList);
			}
			if (specialTopicList.size() > 0) {
				jsonMap.put("error", "0");
				jsonMap.put("msg", "成功");
			} else {
				jsonMap.put("error", "0");
				jsonMap.put("msg", "为空");
			}
		} catch (Exception e) {
			jsonMap.put("error", "1");
			jsonMap.put("msg", "未知异常");
			e.printStackTrace();
		} finally {
			try {
				jsonMap.put("specialTopicList", specialTopicList);
				JSONUtils.printObject(jsonMap, response);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
