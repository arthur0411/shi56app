package com.flf.mapper;

import java.util.List;
import java.util.Map;

import com.base.dao.BaseDao;
import com.flf.entity.ShopCommodityBrand;
import com.flf.entity.ShopCommodityTag;

/**
 * 
 * <br>
 * <b>功能：</b>ShopCommodityTagDao<br>
 */
public interface ShopCommodityBrandMapper extends BaseDao {

	List<Map<String, Object>> listPage(ShopCommodityBrand vo);

	ShopCommodityBrand getShopCommodityBrandByName(String brandName);

	List<Map<String, Object>> getCommodityBrandList();

	List<Map<String, Object>> listAll();
	
}
