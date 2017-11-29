package com.flf.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.base.criteria.Criteria;
import com.base.service.BaseService;
import com.flf.entity.ShopCommodity;
import com.flf.entity.ShopCommoditySku;
import com.flf.entity.ShopOrderDetails;

/**
 * 
 * <br>
 * <b>功能：</b>ShopCommodityService<br>
 */
public interface ShopCommoditySkuService extends BaseService {

	 List<ShopCommoditySku> list(Integer commodityId);
	
}
