package com.flf.controller.app;

import java.io.IOException;
import java.util.Date;
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

import com.base.criteria.Criteria;
import com.flf.entity.ShopWishList;
import com.flf.service.ShopWishListService;
import com.flf.util.DateUtil;
import com.flf.util.JSONUtils;

/**
 * 
 * <br>
 * <b>功能：</b>ShopWishListController<br>
 * <br>
 */
@Controller
@RequestMapping(value = "/wishListApp")
public class ShopWishListAppController {

	private final static Logger log = Logger.getLogger(ShopWishListAppController.class);
	@Autowired(required = false)
	private ShopWishListService shopWishListService;

	/**
	 * 添加心愿单
	 */
	@RequestMapping(value = "/addWishList")
	public void addWishList(@ModelAttribute ShopWishList shopWishList, HttpServletResponse response) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		try {
			log.info("用户id：" + shopWishList.getUserId() + "添加心愿单");
			if (shopWishList.getUserId() != null) {
				shopWishList.setStatus(1);
				shopWishList.setCreateTime(DateUtil.datetime2Str(new Date()));
				int resultId = shopWishListService.add(shopWishList);

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
	 * 删除心愿单
	 */
	@RequestMapping(value = "/updateWishList")
	public void updateWishList(Integer userId, Integer commodityId, HttpServletResponse response) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		try {
			log.info("删除用户id:" + userId + "心愿单：");
			if (userId != null && commodityId != null) {
				int resultId = shopWishListService.updateWishList(userId, commodityId);

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
	 * 获取用户心愿单
	 */
	@RequestMapping(value = "/getWishListByUserId")
	public void getWishListByUserId(@RequestParam Integer userId, int currentPage, HttpServletResponse response) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		Criteria criteria = new Criteria();
		criteria.setCurrentPage(currentPage);
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("userId", userId);
		criteria.setCondition(condition);
		List<Map<String, Object>> shopCommodity = null;
		try {
			log.info("获取用户Id:" + userId + "个人心愿单");
			List<Map<String, Object>> userWishList = shopWishListService.getWishListByUserId(criteria);

			jsonMap.put("error", "0");
			jsonMap.put("msg", (userWishList != null) ? "成功" : "为空");
			jsonMap.put("userWishList", userWishList);
		} catch (Exception e) {
			jsonMap.put("error", "1");
			jsonMap.put("msg", "未知异常");
			e.printStackTrace();
		} finally {
			try {
				jsonMap.put("ShopCommodity", shopCommodity);
				jsonMap.put("page", criteria);
				JSONUtils.printObject(jsonMap, response);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
