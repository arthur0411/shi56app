package com.flf.mapper;

import java.util.List;
import java.util.Map;

import com.base.dao.BaseDao;
import com.flf.entity.ShopWithdrawals;

/**
 * 
 * <br>
 * <b>功能：</b>ShopWithdrawalsDao<br>
 */
public interface ShopWithdrawalsMapper extends BaseDao {

	List<Map<String, Object>> listPageAllOrder(ShopWithdrawals withdrawals);

	int updateWithdrawals(ShopWithdrawals withdrawals);

	List<Map<String, Object>> exportWithdrawals(ShopWithdrawals withdrawals);

}
