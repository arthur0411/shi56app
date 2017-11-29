package com.flf.service;

import java.util.List;

import com.base.service.BaseService;
import com.flf.entity.ShopTradingHistory;

/**
 * 
 * <br>
 * <b>功能：</b>ShopTradingHistoryService<br>
 */
public interface ShopTradingHistoryService extends BaseService {

	List<ShopTradingHistory> listPageTradingHistory(ShopTradingHistory vo);

	void saveTradeRecord(ShopTradingHistory trading);

}
