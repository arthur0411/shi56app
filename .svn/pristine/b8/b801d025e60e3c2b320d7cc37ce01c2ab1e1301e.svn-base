package com.flf.controller;

import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.flf.entity.ShopVip;
import com.flf.service.ShopVipService;
import com.flf.util.DateUtil;

/**
 * 
 * <br>
 * <b>功能：</b>ShopVipController<br>
 * <br>
 */
@Controller
@RequestMapping(value = "/shopVip")
public class ShopVipController {

	private final static Logger log = Logger.getLogger(ShopVipController.class);
	@Autowired(required = false)
	private ShopVipService shopVipService;

	@RequestMapping(value = "/list")
	public ModelAndView list(ShopVip vo, HttpSession session) {
		ModelAndView mv = new ModelAndView();

		List<ShopVip> shopVipList = shopVipService.listPage(vo);

		mv.addObject("vo", vo);
		mv.addObject("shopVipList", shopVipList);
		mv.setViewName("shopVipList");

		return mv;
	}

	@RequestMapping(value = "/toAdd")
	public ModelAndView add() throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("shopVipAdd");
		return mv;
	}

	@RequestMapping(value = "/edit")
	public ModelAndView edit(@RequestParam Integer id) throws Exception {
		ModelAndView mv = new ModelAndView();
		ShopVip vo = shopVipService.queryById(id);
		mv.addObject("shopVip", vo);
		mv.setViewName("shopVipAdd");
		return mv;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public void save(ShopVip vo, PrintWriter out) throws Exception {
		String result = "success";
		if (vo != null) {
			if (vo.getVipId() == null) {
				boolean istrue = shopVipService.getShopVipByName(vo.getVipName());
				if (istrue) {
					result = "error";
				} else {
					vo.setCreateTime(DateUtil.datetime2Str(new Date()));
					shopVipService.add(vo);
				}
			} else {
				shopVipService.updateBySelective(vo);
			}
		} else {
			result = "error";
		}

		out.print(result);
		out.close();
	}

	@RequestMapping(value = "/delete")
	public void delete(Integer[] id, PrintWriter out) throws Exception {
		shopVipService.delete((Object[]) id);
		out.print("success");
		out.close();
	}

}
