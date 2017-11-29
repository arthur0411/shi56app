package com.flf.controller.app;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.flf.entity.ShopUserAddress;
import com.flf.service.ShopUserAddressService;
import com.flf.util.JSONUtils;

/**
 * 
 * <br>
 * <b>功能：</b>ShopUserAddressAppController<br>
 * <br>
 */
@Controller
@RequestMapping(value = "/shopUserAddress")
public class ShopUserAddressAppController {

	private final static Logger log = Logger.getLogger(ShopUserAddressAppController.class);
	@Autowired(required = false)
	private ShopUserAddressService shopUserAddressService;

	/**
	 * 查询用户收货地址
	 * 
	 */
	@RequestMapping(value = "/getShopUserAddress")
	public void getShopUserAddressApp(@RequestParam Integer userId, HttpServletResponse response) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		try {
			log.info("获取用户Id:" + userId + "个人收货地址信息");
			if (null != userId) {
				List<ShopUserAddress> shopAddressList = shopUserAddressService.getShopAddressList(userId);
				jsonMap.put("error", "0");
				jsonMap.put("msg", (shopAddressList != null) ? "成功" : "为空");
				jsonMap.put("shopAddressList", shopAddressList);
			} else {
				jsonMap.put("error", "1");
				jsonMap.put("msg", "用户为空");
			}

		} catch (Exception e) {
			jsonMap.put("error", "1");
			jsonMap.put("msg", "未知异常");
			e.printStackTrace();
		} finally {
			try {
				JSONUtils.printObject(jsonMap, response);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	/**
	 * 添加用户收货地址
	 */
	@RequestMapping(value = "/addShopUserAddress")
	public void addShopUserAddress(@ModelAttribute ShopUserAddress shopUserAddress, HttpServletResponse response) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		int resultId = 0;
		try {
			log.info("用户id：" + shopUserAddress.getUserId() + "添加收货地址");
			if (shopUserAddress.getUserId() != null) {
				if (shopUserAddress.getAddressId() != null && shopUserAddress.getAddressId() > 0) {
					resultId = shopUserAddressService.update(shopUserAddress);
				} else {
					shopUserAddress.setIsDefault("1");
					resultId = shopUserAddressService.add(shopUserAddress);
				}

				if (resultId > 0) {
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
	}

	/**
	 * 设置为默认收货地址 只有一个
	 */
	@RequestMapping(value = "/updateDefaultAddress")
	public void updateDefaultAddress(@RequestParam Integer addressId, Integer userId, HttpServletResponse response) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		try {
			log.info("用户Id:" + userId + "设置地址addressId：" + addressId + "成默认收货地址");
			if (null != addressId) {
				int resultId = shopUserAddressService.updateDefaultAddressByUserId(userId);
				if (resultId > 0) {
					ShopUserAddress shopUserAddress = shopUserAddressService.queryById(addressId);
					if (null != shopUserAddress) {
						shopUserAddress.setIsDefault("0");
						shopUserAddressService.updateBySelective(shopUserAddress);
					}
				}

				jsonMap.put("error", "0");
				jsonMap.put("msg", "成功");

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

}
