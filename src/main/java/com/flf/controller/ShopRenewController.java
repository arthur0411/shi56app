package com.flf.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.base.util.HttpUtil;
import com.flf.entity.ShopRenew;
import com.flf.service.ShopRenewService;
import com.flf.util.ExcelUtil;

/**
 * 
 * <br>
 * <b>功能：</b>ShopOrderPaymentController<br>
 * <br>
 */
@Controller
@RequestMapping(value = "/renew")
public class ShopRenewController {

	private final static Logger log = Logger.getLogger(ShopRenewController.class);
	@Autowired(required = false)
	private ShopRenewService service;

	@RequestMapping(value = "/list")
	/*public ModelAndView list(ShopOrderPaymentVo vo, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();

		List<ShopOrderPaymentVo> orderPaymentList = service.list(vo);
		mv.addObject("vo", vo);
		mv.addObject("orderPaymentList", orderPaymentList);
		mv.setViewName("orderPaymentList");

		return mv;
	}*/
	public ModelAndView list(HttpSession session, ShopRenew vo) {
		ModelAndView mv = new ModelAndView();
		try {
			List<Map<String,Object>> renewList = service.listPage(vo);
			
			mv.addObject("renewList", renewList);
			mv.addObject("vo", vo);
			mv.setViewName("shopRenewList");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}

	@RequestMapping(value = "/excel")
	public void exportRecord(HttpServletRequest request, HttpServletResponse response) {
		try {
			XSSFWorkbook wb = service.exportRecord();
			String filename = HttpUtil.encodeFilename(request, "月卡记录");
			ExcelUtil.output(response, filename, wb);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}



}
