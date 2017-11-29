package com.flf.service;

import java.util.List;
import java.util.Map;

import com.base.service.BaseService;
import com.flf.entity.ShopOrderDetails;

/**
 * 
 * <br>
 * <b>功能：</b>ShopOrderDetailsService<br>
 */
public interface ShopOrderDetailsService extends BaseService {

	List<Map<String, Object>> listAllOrderDetails(int orderId);

	List<ShopOrderDetails> getDetailByOrderId(Integer orderId);

	List<Map<String, Object>> getCommodityrentByOrderId(Integer orderId);

}
