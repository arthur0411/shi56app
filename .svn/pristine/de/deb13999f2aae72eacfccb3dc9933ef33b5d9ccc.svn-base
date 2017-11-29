package com.flf.controller;

import java.io.File;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.flf.entity.ShopCarousel;
import com.flf.service.ShopCarouselService;
import com.flf.util.DateUtil;
import com.flf.util.FileUpload;
import com.flf.util.JSONUtils;

/**
 * 
 * <br>
 * <b>功能：</b>ShopCarouselController<br>
 * <br>
 */
@Controller
@RequestMapping(value = "/shopcarousel")
public class ShopCarouselController {

	private final static Logger log = Logger.getLogger(ShopCarouselController.class);
	@Autowired(required = false)
	private ShopCarouselService shopCarouselService;

	@RequestMapping(value = "/list")
	public ModelAndView list(ShopCarousel vo, HttpSession session) {
		ModelAndView mv = new ModelAndView();

		List<ShopCarousel> shopCarouselList = shopCarouselService.listPage(vo);

		mv.addObject("vo", vo);
		mv.addObject("shopCarouselList", shopCarouselList);
		mv.setViewName("shopCarouselList");

		return mv;
	}

	@RequestMapping(value = "/toAdd")
	public ModelAndView add() throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("addshopCarousel");
		return mv;
	}

	@RequestMapping(value = "/edit")
	public ModelAndView edit(@RequestParam Integer id, HttpSession session) throws Exception {
		ModelAndView mv = new ModelAndView();

		ShopCarousel vo = shopCarouselService.queryById(id);
		mv.addObject("vo", vo);
		mv.setViewName("addshopCarousel");
		return mv;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public void save(ShopCarousel po, PrintWriter out, HttpSession session) throws Exception {
		String result = "success";
		if (po != null) {

			if (po.getCarouselId() == null) {
				shopCarouselService.add(po);
			} else {
				shopCarouselService.updateBySelective(po);
			}
		} else {
			result = "error";
		}

		out.print(result);
		out.close();
	}

	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public void uploadFile(@RequestParam("fileName") MultipartFile file, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("application/json;charset=UTF-8");

		String subPath = "special_topic" + File.separator //
				+ DateUtil.date2Str(new Date(), false) + File.separator;

		String filePath = FileUpload.uploadFile(file, subPath, request);
		Map<String, Object> result = new HashMap<>();
		if (!"".equals(filePath)) {
			result.put("filePath", filePath);
			result.put("result", "success");
		} else {
			result.put("result", "error");
		}
		JSONUtils.printObject(result, response);
	}

	@RequestMapping(value = "/delete")
	public void delete(Integer[] id, PrintWriter out) throws Exception {
		shopCarouselService.delete((Object[]) id);
		out.print("success");
		out.close();
	}

}
