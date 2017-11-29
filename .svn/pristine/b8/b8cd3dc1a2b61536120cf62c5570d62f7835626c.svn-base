package com.flf.controller.app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.flf.entity.ShopAddress;
import com.flf.entity.ShopAreasList;
import com.flf.entity.ShopCity;
import com.flf.service.ShopAddressService;
import com.flf.util.JSONUtils;

/**
 * 
 * <br>
 * <b>功能：</b>ShopAddressAppController<br>
 * <br>
 */
@Controller
@RequestMapping(value = "/shopAddress")
public class ShopAddressAppController {

	private final static Logger log = Logger.getLogger(ShopAddressAppController.class);
	@Autowired(required = false)
	private ShopAddressService service;

	/**
	 * 获取省的接口
	 * 
	 * @param sign
	 * @param response
	 */
	@RequestMapping(value = "/getAreaList")
	@ResponseBody
	public void getAreaList(HttpServletResponse response) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		try {
			List<ShopAreasList> HajAreasList = new ArrayList();
			List<ShopCity> cityList = new ArrayList();
			ShopAreasList areaList = null;
			List<ShopAddress> provinceList = service.getAreaProvinceList();
			if (null != provinceList && provinceList.size() > 0) {
				for (ShopAddress area : provinceList) {
					areaList = new ShopAreasList();
					areaList.setCode(area.getCode());
					areaList.setName(area.getName());
					List<ShopAddress> cityLists = service.getAreaByPCode(area.getCode());
					for (ShopAddress city : cityLists) {
						ShopCity c = new ShopCity();
						c.setName(city.getName());
						c.setCode(city.getCode());
						List<ShopAddress> hajAreas = service.getAreaByPCode(city.getCode());
						c.setHajAreas(hajAreas);
						cityList.add(c);
					}
					areaList.setHajCitys(cityList);
					HajAreasList.add(areaList);
				}
				jsonMap.put("error", "0");
				jsonMap.put("areaList", HajAreasList);
				jsonMap.put("msg", "成功");
			} else {
				jsonMap.put("error", "0");
				jsonMap.put("msg", "失败");
				jsonMap.put("areaList", "");
			}

			JSONUtils.printObject(jsonMap, response);

		} catch (Exception e) {
			jsonMap.put("error", "1");
			jsonMap.put("msg", "未知异常");
			try {
				JSONUtils.printObject(jsonMap, response);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

	}

	/**
	 * 获取省的接口
	 * 
	 * @param sign
	 * @param response
	 */
	@RequestMapping(value = "/getAreaProvince")
	@ResponseBody
	public void getAreaProvince(HttpServletResponse response) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		try {
			List<ShopAddress> provinceList = service.getAreaProvinceList();
			if (null != provinceList && provinceList.size() > 0) {
				jsonMap.put("error", "0");
				jsonMap.put("provinceList", provinceList);
				jsonMap.put("msg", "成功");
			} else {
				jsonMap.put("error", "0");
				jsonMap.put("msg", "失败");
				jsonMap.put("provinceList", "");
			}

			JSONUtils.printObject(jsonMap, response);

		} catch (Exception e) {
			jsonMap.put("error", "1");
			jsonMap.put("msg", "未知异常");
			try {
				JSONUtils.printObject(jsonMap, response);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

	}

	/**
	 * 根据省获取市的接口
	 * 
	 * @param sign
	 * @param response
	 */
	@RequestMapping(value = "/getAreaCityByPcode")
	@ResponseBody
	public void getAreaCityByPcode(String pCode, HttpServletResponse response) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		try {
			List<ShopAddress> cityList = service.getAreaByPCode(pCode);
			if (null != cityList && cityList.size() > 0) {
				jsonMap.put("error", "0");
				jsonMap.put("cityList", cityList);
				jsonMap.put("msg", "成功");
			} else {
				jsonMap.put("error", "0");
				jsonMap.put("msg", "失败");
				jsonMap.put("cityList", "");
			}

			JSONUtils.printObject(jsonMap, response);

		} catch (Exception e) {
			jsonMap.put("error", "1");
			jsonMap.put("msg", "未知异常");
			try {
				JSONUtils.printObject(jsonMap, response);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

	}

}
