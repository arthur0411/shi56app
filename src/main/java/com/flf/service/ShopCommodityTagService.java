package com.flf.service;

import java.util.List;
import java.util.Map;

import com.base.service.BaseService;
import com.flf.entity.ShopCommodityTag;

/**
 * 
 * <br>
 * <b>功能：</b>ShopCommodityTagService<br>
 */
public interface ShopCommodityTagService extends BaseService {

	List<Map<String, Object>> listPage(ShopCommodityTag vo);

	boolean getShopCommodityTagByName(String tagName);

	List<Map<String, Object>> getCommodityTagList();

}
