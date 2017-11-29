package com.flf.mapper;

import java.util.List;

import com.base.dao.BaseDao;
import com.flf.entity.ShopTradingHistory;

/**
 * 
 * <br>
 * <b>功能：</b>ShopTradingHistoryDao<br>
 */
public interface ShopTradingHistoryMapper extends BaseDao {

	List<ShopTradingHistory> listPageTradingHistory(ShopTradingHistory vo);

}
