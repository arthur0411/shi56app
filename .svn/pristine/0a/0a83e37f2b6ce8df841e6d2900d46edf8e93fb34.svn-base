package com.flf.controller.cart;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.flf.entity.ShopCommodity;
import com.flf.entity.ShopCommodityTag;
import com.flf.service.ShopCommodityService;
import com.flf.service.ShopCommodityTagService;
import com.flf.util.JSONUtils;

@Controller
@RequestMapping("/cart")
public class CartController {

	private static final Logger log = Logger.getLogger(CartController.class);
	
	@Autowired(required = false)
	private ShopCommodityService shopCommodityService;
	
	@RequestMapping(value = "/toMain")
	public String loginGet() {
		return "cart/main";
	}

	@RequestMapping(value = "/listcart")
	public void listcart(HttpServletResponse httpServletResponse) throws Exception {
		List<ShopCommodity>  commodityList = shopCommodityService.listcart();
		JSONUtils.printArray(commodityList, httpServletResponse);
	}
	
}