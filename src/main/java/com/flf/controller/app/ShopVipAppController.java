package com.flf.controller.app;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.flf.service.ShopVipService;
import com.flf.util.JSONUtils;

/**
 * 
 * <br>
 * <b>功能：</b>ShopVipAppController<br>
 * <br>
 */
@Controller
@RequestMapping(value = "/shopVipApp")
public class ShopVipAppController {

	private final static Logger log = Logger.getLogger(ShopVipAppController.class);
	@Autowired(required = false)
	private ShopVipService shopVipService;

	/**
	 * 获取vip登记接口
	 */
	@RequestMapping(value = "/getShopVip")
	public void getShopVip(HttpServletResponse response) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		List<Map<String, Object>> shopVipList = null;
		try {
			log.info("获取vip登记接口");
			shopVipList = shopVipService.getShopVipList();

			if (shopVipList.size() > 0) {
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
				jsonMap.put("shopVipList", shopVipList);
				JSONUtils.printObject(jsonMap, response);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
