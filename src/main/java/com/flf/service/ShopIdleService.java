package com.flf.service;

import java.util.List;
import java.util.Map;

import com.base.criteria.Criteria;
import com.base.service.BaseService;
import com.flf.entity.ShopIdle;

/**
 * 
 * <br>
 * <b>功能：</b>ShopIdleService<br>
 */
public interface ShopIdleService extends BaseService {

	List<Map<String,String>> listPage(ShopIdle vo);

	int saveShopIdle(ShopIdle shopIdle);
	
	void changeStatus(Integer id , String status);
	
	List<Map<String, Object>> getOrderTimeByUserId(Integer userId);
	
	int getIdleCount(Integer userId);
	
}
