package com.flf.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.flf.entity.ShopIdle;
import com.flf.entity.ShopUser;
import com.flf.service.ShopIdleService;
import com.flf.service.ShopUserService;
import com.flf.util.JSONUtils;

/**
 * 
 * <br>
 * <b>功能：</b>ShopIdleController<br>
 * <br>
 */
@Controller
@RequestMapping(value = "/idle")
public class ShopIdleController {

	private final static Logger log = Logger.getLogger(ShopIdleController.class);
	@Autowired(required = false)
	private ShopIdleService shopIdleService;
	@Autowired(required = false)
	private ShopUserService shopUserService;

	@RequestMapping(value = "/list")
	public ModelAndView list(ShopIdle vo, HttpSession session) {
		ModelAndView mv = new ModelAndView();

		List<Map<String,String>> promotionAreaList = shopIdleService.listPage(vo);

		mv.addObject("vo", vo);
		mv.addObject("idleList", promotionAreaList);
		mv.setViewName("idleList");

		return mv;
	}
	
	@RequestMapping(value = "/detail")
	public ModelAndView detail(Integer id, HttpSession session) {
		ModelAndView mv = new ModelAndView();

		ShopIdle si;
		try {
			si = shopIdleService.queryById(id);
			mv.addObject("vo", si);
			mv.setViewName("idleDetail");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}
	
	@RequestMapping(value = "/productPic")
	public ModelAndView productPic(Integer id, HttpSession session) {
		ModelAndView mv = new ModelAndView();

		ShopIdle si;
		try {
			si = shopIdleService.queryById(id);
			mv.addObject("vo", si);
			mv.setViewName("idleProductPic");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}

	@RequestMapping(value = "/checkPass")
	public void passCheck(@RequestParam Integer id, HttpServletResponse httpServletResponse) throws Exception {
		shopIdleService.changeStatus(id, "3");
		JSONUtils.printStr("1", httpServletResponse);
	}
	
	
	@RequestMapping(value = "/checkFail")
	public void failCheck(@RequestParam Integer id, HttpServletResponse httpServletResponse) throws Exception {
		shopIdleService.changeStatus(id, "2");
		JSONUtils.printStr("1", httpServletResponse);
	}
	
	@RequestMapping(value = "/acceptFail")
	public void acceptFail(@RequestParam Integer id, HttpServletResponse httpServletResponse) throws Exception {
		shopIdleService.changeStatus(id, "5");
		JSONUtils.printStr("1", httpServletResponse);
	}
	
	@RequestMapping(value = "/acceptPass")
	public void acceptPass(@RequestParam Integer id, HttpServletResponse httpServletResponse) throws Exception {
		try {
			
			ShopIdle si = shopIdleService.queryById(id);
			ShopUser su = shopUserService.queryById(si.getUserId());
			int days = 0;
			if(su.getReductionDays() != null) {
				days = su.getReductionDays();
			}
			if(su.getUserVip() == 0) {
				su.setUserVip(2);
			}
			su.setReductionDays(days+31);
			shopIdleService.changeStatus(id, "6");
			shopUserService.updateBySelective(su);
			JSONUtils.printStr("1", httpServletResponse);
		} catch (Exception e) {
			e.printStackTrace();
			JSONUtils.printStr("0", httpServletResponse);
		}
	}
	
	
}
