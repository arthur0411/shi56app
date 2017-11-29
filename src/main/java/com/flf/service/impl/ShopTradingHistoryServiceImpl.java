package com.flf.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.service.BaseServiceImpl;
import com.flf.entity.ShopTradingHistory;
import com.flf.mapper.ShopTradingHistoryMapper;
import com.flf.service.ShopTradingHistoryService;

/**
 * 
 * <br>
 * <b>功能：</b>ShopTradingHistoryService<br>
 */
@Service("shopTradingHistoryService")
public class ShopTradingHistoryServiceImpl extends BaseServiceImpl implements ShopTradingHistoryService {
	private final static Logger log = Logger.getLogger(ShopTradingHistoryServiceImpl.class);

	@Autowired
	private ShopTradingHistoryMapper dao;

	@Override
	public ShopTradingHistoryMapper getDao() {
		return dao;
	}

	@Override
	public List<ShopTradingHistory> listPageTradingHistory(ShopTradingHistory vo) {
		return dao.listPageTradingHistory(vo);
	}

	@Override
	public void saveTradeRecord(ShopTradingHistory trading) {
		if (trading.getId() != null && trading.getId().intValue() > 0) {
			dao.update(trading);
		} else {
			dao.add(trading);
		}
	}

}
