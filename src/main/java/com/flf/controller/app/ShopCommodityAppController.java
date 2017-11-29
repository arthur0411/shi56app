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
import org.springframework.web.bind.annotation.RequestParam;

import com.base.criteria.Criteria;
import com.flf.entity.ShopCommodity;
import com.flf.service.ShopCommodityService;
import com.flf.util.JSONUtils;

/**
 * 
 * <br>
 * <b>功能：</b>ShopCommodityAppController<br>
 * <br>
 */
@Controller
@RequestMapping(value = "/commodity")
public class ShopCommodityAppController {

	private final static Logger log = Logger.getLogger(ShopCommodityAppController.class);
	@Autowired(required = false)
	private ShopCommodityService shopCommodityService;

	/**
	 * 首页商品接口
	 */
	@RequestMapping(value = "/getCommodityIndex")
	public void getCommodityIndex(Integer userId, @RequestParam Integer currentPage, Integer pageSize,
			HttpServletResponse response) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		Criteria criteria = new Criteria();
		criteria.setCurrentPage(currentPage);
		if (null != pageSize && pageSize > 0) {
			criteria.setPageSize(pageSize);
		}
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("userId", userId);
		criteria.setCondition(condition);
		List<Map<String, Object>> shopCommodity = null;
		try {
			log.info("获取首页商品接口");
			shopCommodity = shopCommodityService.getCommodityIndex(criteria);

			if (shopCommodity.size() > 0) {
				jsonMap.put("error", "0");
				jsonMap.put("msg", "成功");
			} else {
				jsonMap.put("error", "0");
				jsonMap.put("msg", "为空");
			}
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

	/**
	 * 首页推荐商品接口
	 */
	@RequestMapping(value = "/getRecommendCommodity")
	public void getRecommendCommodity(Integer userId, HttpServletResponse response) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		Criteria criteria = new Criteria();
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("userId", userId);
		criteria.setCondition(condition);
		List<Map<String, Object>> shopCommodity = null;
		try {
			log.info("获取首页推荐商品接口");
			shopCommodity = shopCommodityService.getRecommendCommodity(criteria);

			if (shopCommodity.size() > 0) {
				jsonMap.put("error", "0");
				jsonMap.put("msg", "成功");
			} else {
				jsonMap.put("error", "0");
				jsonMap.put("msg", "为空");
			}
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

	/**
	 * 根据分类查找上架的商品
	 * 
	 */
	@RequestMapping(value = "/getCommodityByTagId")
	public void getCommodityByTagId(@RequestParam Integer tagId, int userId, @RequestParam Integer currentPage,
			HttpServletResponse response) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		Criteria criteria = new Criteria();
		criteria.setCurrentPage(currentPage);
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("commodityTagId", tagId);
		condition.put("userId", userId);
		criteria.setCondition(condition);
		List<Map<String, Object>> shopCommodity = null;
		try {
			log.info("根据分类Id:" + tagId + "查找上架的商品");
			shopCommodity = shopCommodityService.getCommodityByTagId(criteria);

			if (shopCommodity.size() > 0) {
				jsonMap.put("error", "0");
				jsonMap.put("msg", "成功");
			} else {
				jsonMap.put("error", "0");
				jsonMap.put("msg", "为空");
			}
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

	/**
	 * 首页商品详情接口
	 * 
	 */
	@RequestMapping(value = "/getCommodityInfoById")
	public void getCommodityInfoById(int commodityId, HttpServletResponse response) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		ShopCommodity shopCommodity = null;
		try {
			log.info("获取商品详情接口");
			shopCommodity = shopCommodityService.getCommodityById(commodityId);
			List<String> imglist = new ArrayList<String>();
			if (shopCommodity.getCommodityImg() != null && !shopCommodity.getCommodityImg().equals("")) {
				imglist.add(shopCommodity.getCommodityImg());
			}
			if (shopCommodity.getCommodityImg2() != null && !shopCommodity.getCommodityImg2().equals("")) {
				imglist.add(shopCommodity.getCommodityImg2());
			}

			if (shopCommodity.getCommodityImg3() != null && !shopCommodity.getCommodityImg3().equals("")) {
				imglist.add(shopCommodity.getCommodityImg3());
			}
			if (shopCommodity.getCommodityImg4() != null && !shopCommodity.getCommodityImg4().equals("")) {
				imglist.add(shopCommodity.getCommodityImg4());
			}
			if (shopCommodity.getCommodityImg5() != null && !shopCommodity.getCommodityImg5().equals("")) {
				imglist.add(shopCommodity.getCommodityImg5());
			}
			if (shopCommodity.getCommodityImg6() != null && !shopCommodity.getCommodityImg6().equals("")) {
				imglist.add(shopCommodity.getCommodityImg6());
			}
			if (shopCommodity.getCommodityImg7() != null && !shopCommodity.getCommodityImg7().equals("")) {
				imglist.add(shopCommodity.getCommodityImg7());
			}
			if (shopCommodity.getCommodityImg8() != null && !shopCommodity.getCommodityImg8().equals("")) {
				imglist.add(shopCommodity.getCommodityImg8());
			}
			if (shopCommodity.getCommodityImg9() != null && !shopCommodity.getCommodityImg9().equals("")) {
				imglist.add(shopCommodity.getCommodityImg9());
			}
			if (shopCommodity.getCommodityImg10() != null && !shopCommodity.getCommodityImg10().equals("")) {
				imglist.add(shopCommodity.getCommodityImg10());
			}
			shopCommodity.setImglist(imglist);
			if (null != shopCommodity) {
				jsonMap.put("error", "0");
				jsonMap.put("msg", "成功");
			} else {
				jsonMap.put("error", "0");
				jsonMap.put("msg", "为空");
			}
		} catch (Exception e) {
			jsonMap.put("error", "1");
			jsonMap.put("msg", "未知异常");
			e.printStackTrace();
		} finally {
			try {
				jsonMap.put("ShopCommodity", shopCommodity);
				JSONUtils.printObject(jsonMap, response);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 商品搜索接口
	 * 
	 */
	@RequestMapping(value = "/getSearchCommodity")
	public void getSearchCommodity(@RequestParam String keyword, Integer userId, @RequestParam Integer currentPage,
			HttpServletResponse response) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		Criteria criteria = new Criteria();
		criteria.setCurrentPage(currentPage);
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("commodityName", keyword);
		condition.put("userId", userId);
		criteria.setCondition(condition);
		List<Map<String, Object>> shopCommodity = null;
		try {
			log.info("进入商品搜索接口，关键字：" + keyword);
			shopCommodity = shopCommodityService.getSearchCommodity(criteria);
			if (null != shopCommodity && shopCommodity.size() > 0) {
				jsonMap.put("error", "0");
				jsonMap.put("msg", "成功");
			} else {
				jsonMap.put("error", "0");
				jsonMap.put("msg", "为空");
			}
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

	/**
	 * 获取选饰品 品牌推荐
	 */
	@RequestMapping(value = "/getBrandTopicCommodity")
	public void getBrandTopicCommodity(Integer userId, HttpServletResponse response) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		List<Map<String, Object>> shopCommodity = null;
		Criteria criteria = new Criteria();
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("userId", userId);
		criteria.setCondition(condition);
		try {
			log.info("获取品牌推荐接口");
			shopCommodity = shopCommodityService.getBrandTopicCommodity(criteria);

			if (shopCommodity.size() > 0) {
				jsonMap.put("error", "0");
				jsonMap.put("msg", "成功");
			} else {
				jsonMap.put("error", "0");
				jsonMap.put("msg", "为空");
			}
		} catch (Exception e) {
			jsonMap.put("error", "1");
			jsonMap.put("msg", "未知异常");
			e.printStackTrace();
		} finally {
			try {
				jsonMap.put("BrandTopicCommodity", shopCommodity);
				JSONUtils.printObject(jsonMap, response);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 获取单个商品的库存，下架状态
	 * 
	 */
	/*@RequestMapping(value = "/getSaleStock")
	public void getCommodityStock(String commodityId, HttpServletResponse response) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		List<Map<String, Object>> commodityMap = null;
		String rentStock = null;
		try {
			log.info("获取单个商品可售的库存，下架状态");
			if (null != commodityId) {
				commodityMap = shopCommodityService.getSaleStockById(commodityId.split(","));
				for(Map map : commodityMap) {
					if(map.get("sale_stock").equals("0")) {
						rentStock = shopCommodityService.getRentStock(map.get("commodity_id"));
						if(rentStock != null & !rentStock.equals("0")) {
							map.put("sale_stock", rentStock);
						}
					}
				}
				if (null != commodityMap) {
					jsonMap.put("error", "0");
					jsonMap.put("msg", "成功");
				} else {
						jsonMap.put("error", "0");
						jsonMap.put("msg", "为空");
				}
			} else {
				jsonMap.put("error", "2");
				jsonMap.put("msg", "商品ID为空");
			}
		} catch (Exception e) {
			jsonMap.put("error", "1");
			jsonMap.put("msg", "未知异常");
			e.printStackTrace();
		} finally {
			try {
				jsonMap.put("ShopCommodity", commodityMap);
				JSONUtils.printObject(jsonMap, response);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}*/
	
	@RequestMapping(value = "/getSaleStock")
	public void getRentStock(String commodityId, HttpServletResponse response) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		List<Map<String, Object>> commodityMap = null;
		try {
			log.info("获取单个商品的可租库存，下架状态");
			if (null != commodityId) {
				commodityMap = shopCommodityService.getRentStockById(commodityId.split(","));
				if (null != commodityMap) {
					jsonMap.put("error", "0");
					jsonMap.put("msg", "成功");
				} else {
					jsonMap.put("error", "0");
					jsonMap.put("msg", "为空");
				}
			} else {
				jsonMap.put("error", "2");
				jsonMap.put("msg", "商品ID为空");
			}
		} catch (Exception e) {
			jsonMap.put("error", "1");
			jsonMap.put("msg", "未知异常");
			e.printStackTrace();
		} finally {
			try {
				jsonMap.put("ShopCommodity", commodityMap);
				JSONUtils.printObject(jsonMap, response);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	
	@RequestMapping(value = "/getCommodityInfo")
	public void getCommodityInfo(Integer commodityId, HttpServletResponse response) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		ShopCommodity commodity = null;
		try {
			log.info("获取单个商品的信息");
			if (null != commodityId) {
				commodity = shopCommodityService.getCommodityById(commodityId);
				if (null != commodity) {
					jsonMap.put("error", "0");
					jsonMap.put("msg", "成功");
				} else {
					jsonMap.put("error", "0");
					jsonMap.put("msg", "为空");
				}
			} else {
				jsonMap.put("error", "2");
				jsonMap.put("msg", "商品ID为空");
			}
		} catch (Exception e) {
			jsonMap.put("error", "1");
			jsonMap.put("msg", "未知异常");
			e.printStackTrace();
		} finally {
			try {
				jsonMap.put("ShopCommodity", commodity);
				JSONUtils.printObject(jsonMap, response);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
