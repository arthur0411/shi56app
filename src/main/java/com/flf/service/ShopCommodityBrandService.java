package com.flf.service;

import java.util.List;
import java.util.Map;

import com.base.service.BaseService;
import com.flf.entity.ShopCommodityBrand;
import com.flf.entity.ShopCommodityTag;

/**
 * 
 * <br>
 * <b>功能：</b>ShopCommodityTagService<br>
 */
public interface ShopCommodityBrandService extends BaseService {

	List<Map<String, Object>> listPage(ShopCommodityBrand vo);

	boolean getShopCommodityBrandByName(String brandName);

	List<Map<String, Object>> getCommodityBrandList();
	
	List<Map<String, Object>> listAll();

}
