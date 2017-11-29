package com.flf.mapper;

import java.util.List;
import java.util.Map;

import com.base.dao.BaseDao;
import com.flf.entity.ShopOrderDetails;

/**
 * 
 * <br>
 * <b>功能：</b>ShopOrderDetailsDao<br>
 */
public interface ShopOrderDetailsMapper extends BaseDao {

	List<Map<String, Object>> listAllOrderDetails(int orderId);

	List<ShopOrderDetails> getDetailByOrderId(Integer orderId);

	List<Map<String, Object>> getCommodityrentByOrderId(Integer orderId);

}
