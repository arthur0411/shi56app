package com.flf.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.flf.entity.ShopWishList;
import com.flf.service.ShopWishListService;

/**
 * 
 * <br>
 * <b>功能：</b>ShopWishListController<br>
 * <br>
 */
@Controller
@RequestMapping(value = "/wishList")
public class ShopWishListController {

	private final static Logger log = Logger.getLogger(ShopWishListController.class);
	@Autowired(required = false)
	private ShopWishListService shopWishListService;

	@RequestMapping(value = "/list")
	public ModelAndView list(ShopWishList vo, HttpSession session) {
		ModelAndView mv = new ModelAndView();

		List<Map<String, Object>> wishList = shopWishListService.listPage(vo);

		mv.addObject("vo", vo);
		mv.addObject("wishList", wishList);
		mv.setViewName("wishList");

		return mv;
	}
}
