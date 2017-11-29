package com.flf.mapper;

import java.util.List;
import java.util.Map;

import com.base.criteria.Criteria;
import com.base.dao.BaseDao;
import com.flf.entity.ShopOrder;

/**
 * 
 * <br>
 * <b>功能：</b>ShopOrderDao<br>
 */
public interface ShopOrderMapper extends BaseDao {

	ShopOrder getOrderByOrderNo(String orderNo);

	List<Map<String, Object>> getOrderTimeByUserId(Criteria criteria);

	List<Map<String, Object>> listPageOrder(ShopOrder orderVo);

	int getShopOrderByUserId(int parseInt);
	
	int getSaleOrderByUserId(int parseInt);

	Map<String, Object> getOrderInfoById(int orderId);

	int updateExpress(ShopOrder orderVo);

	void updateCheckOrder(ShopOrder order);

	List<Map<String, Object>> getOrderWearNumber();

	int updateOrderWearingDays(int orderId);

	List<Map<String, Object>> getOrderWearNumberStatus(int order_wearNumber);

	void updateOrderWearStatus(int orderId);

	void updateOrderPayStatus(ShopOrder orderVo);
	
	List<Map<String, Object>> exportOrders();

}
