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

import com.base.criteria.Criteria;
import com.flf.service.ShopCommodityTagService;
import com.flf.util.JSONUtils;

/**
 * 
 * <br>
 * <b>功能：</b>ShopCommodityTagAppController<br>
 * <br>
 */
@Controller
@RequestMapping(value = "/commodityTag")
public class ShopCommodityTagAppController {

	private final static Logger log = Logger.getLogger(ShopCommodityTagAppController.class);
	@Autowired(required = false)
	private ShopCommodityTagService shopCommodityTagService;

	/**
	 * 获取所有商品分类
	 */
	@RequestMapping(value = "/getAllCommodityTag")
	public void getAllCommodityTag(HttpServletResponse response) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		Criteria criteria = new Criteria();
		List<Map<String, Object>> shopCommodityTag = null;
		try {
			log.info("获取所有商品分类");
			shopCommodityTag = shopCommodityTagService.queryByList(criteria);

			if (shopCommodityTag.size() > 0) {
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
				jsonMap.put("shopCommodityTag", shopCommodityTag);
				jsonMap.put("page", criteria);
				JSONUtils.printObject(jsonMap, response);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
