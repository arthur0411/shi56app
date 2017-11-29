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
import com.flf.entity.ShopCarousel;
import com.flf.service.ShopCarouselService;
import com.flf.util.JSONUtils;

/**
 * 首页轮播类 <br>
 * <b>功能：</b>ShopCarouselAppController<br>
 * <br>
 */
@Controller
@RequestMapping(value = "/carousel")
public class ShopCarouselAppController {

	private final static Logger log = Logger.getLogger(ShopCarouselAppController.class);
	@Autowired(required = false)
	private ShopCarouselService shopCarouselService;

	/**
	 * 首页轮播图片
	 */
	@RequestMapping(value = "/getCarouselIndex")
	public void getCarouselIndex(HttpServletResponse response) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		Criteria criteria = new Criteria();
		List<ShopCarousel> shopCarousel = null;
		try {
			shopCarousel = shopCarouselService.queryByList(criteria);

			if (shopCarousel.size() > 0) {
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
				jsonMap.put("shopCarousel", shopCarousel);
				jsonMap.put("page", criteria);
				JSONUtils.printObject(jsonMap, response);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
