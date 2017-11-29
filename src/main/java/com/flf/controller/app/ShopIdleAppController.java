package com.flf.controller.app;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.base.criteria.Criteria;
import com.flf.entity.ShopIdle;
import com.flf.entity.ShopUser;
import com.flf.service.ShopIdleService;
import com.flf.service.ShopUserService;
import com.flf.util.DateUtil;
import com.flf.util.FileUpload;
import com.flf.util.JSONUtils;
import com.flf.util.MakeOrderNum;

/**
 * 
 * <br>
 * <b>功能：</b>ShopIdleController<br>
 * <br>
 */
@Controller
@RequestMapping(value = "/shopIdle")
public class ShopIdleAppController {

	private final static Logger log = Logger.getLogger(ShopIdleAppController.class);
	@Autowired(required = false)
	private ShopIdleService shopIdleService;
	@Autowired(required = false)
	private ShopUserService shopUserService;

	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public void uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();

		String subPath = "images" + File.separator + "idleCommodity" + File.separator + DateUtil.date2Str(new Date(), false)
				+ File.separator;
		String filePath = FileUpload.uploadFile(file, subPath, request);

		if (!"".equals(filePath)) {
			jsonMap.put("error", "0");
			jsonMap.put("msg", "成功");
			jsonMap.put("result", filePath);
		} else {
			jsonMap.put("error", "1");
			jsonMap.put("msg", "未知异常");
			jsonMap.put("result", "0");
		}
		JSONUtils.printObject(jsonMap, response);
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/addShopIdle")
	public void addShopIdle(ShopIdle shopIdle, HttpServletResponse response) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		try {
			log.info("添加闲置商品");
			if (shopIdle.getUserId() != null) {
				int count = shopIdleService.getIdleCount(shopIdle.getUserId());
				if(count < 1) {
					ShopUser su = shopUserService.queryById(shopIdle.getUserId());
					shopIdle.setMobile(su.getMobilePhone());
					shopIdle.setOrderNum("xz" + DateUtil.getInt());
					shopIdle.setCreateTime(DateUtil.datetime2Str(new Date()));
					shopIdle.setStatus("0");
					int resultId = shopIdleService.saveShopIdle(shopIdle);
	
					if (resultId > 0) {
						// 添加图片
						jsonMap.put("error", "0");
						jsonMap.put("msg", "成功");
					} else {
						jsonMap.put("error", "0");
						jsonMap.put("msg", "为空");
					}
				}else {
					jsonMap.put("error", "3");
					jsonMap.put("msg", "用户已有审核中或审核成功的订单");
				}
			} else {
				jsonMap.put("error", "2");
				jsonMap.put("msg", "用户ID为空");
			}
		} catch (Exception e) {
			jsonMap.put("error", "1");
			jsonMap.put("msg", "未知异常");
			e.printStackTrace();
		} finally {
			try {
				JSONUtils.printObject(jsonMap, response);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	@RequestMapping(value = "/getOrderTime")
	public void getOrderTime(@RequestParam Integer userId, HttpServletResponse response) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		try {
			List<Map<String, Object>> HajOrderTime = shopIdleService.getOrderTimeByUserId(userId);

			if (HajOrderTime.size() > 0) {
				jsonMap.put("error", "0");
				jsonMap.put("msg", "成功");
				jsonMap.put("HajOrderTime", HajOrderTime);
			} else {
				jsonMap.put("error", "0");
				jsonMap.put("msg", "为空");
				jsonMap.put("HajOrderTime", "");
			}

		} catch (Exception e) {
			jsonMap.put("error", "1");
			jsonMap.put("msg", "未知异常");
			log.error(e.getMessage(), e);
		} finally {
			try {
				JSONUtils.printObject(jsonMap, response);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	
	@RequestMapping(value = "/express")
	public void express(@RequestParam Integer id,String express,String expressCode, HttpServletResponse response) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		try {
			ShopIdle si = shopIdleService.queryById(id);
			si.setExpress(express);
			si.setExpressCode(expressCode);
			shopIdleService.updateBySelective(si);
			jsonMap.put("error", "0");
			jsonMap.put("msg", "成功");
			jsonMap.put("shopIdle", si);
		} catch (Exception e) {
			jsonMap.put("error", "1");
			jsonMap.put("msg", "异常");
			log.error(e.getMessage(), e);
		} finally {
			try {
				JSONUtils.printObject(jsonMap, response);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	
	/**
	 * 添加闲置商品
	 *//*
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/addShopIdle")
	public void addShopIdle(@RequestParam String shopIdleStr, HttpServletResponse response) {
		JSONObject jsonObj = JSONObject.fromObject(shopIdleStr);

		Map<String, Class> classMap = new HashMap<String, Class>();
		classMap.put("shopIdlePicList", ShopIdlePic.class);
		// 将JSON转换成ShopIdle
		ShopIdle shopIdle = (ShopIdle) JSONObject.toBean(jsonObj, ShopIdle.class, classMap);

		Map<String, Object> jsonMap = new HashMap<String, Object>();
		try {
			log.info("添加闲置商品");
			if (shopIdle.getUserId() != null) {
				shopIdle.setCreateTime(DateUtil.datetime2Str(new Date()));
				shopIdle.setAuditStatus(1);
				int resultId = shopIdleService.saveShopIdle(shopIdle);

				if (resultId > 0) {
					// 添加图片
					jsonMap.put("error", "0");
					jsonMap.put("msg", "成功");
				} else {
					jsonMap.put("error", "0");
					jsonMap.put("msg", "为空");
				}
			} else {
				jsonMap.put("error", "2");
				jsonMap.put("msg", "用户ID为空");
			}
		} catch (Exception e) {
			jsonMap.put("error", "1");
			jsonMap.put("msg", "未知异常");
			e.printStackTrace();
		} finally {
			try {
				JSONUtils.printObject(jsonMap, response);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}*/

}
