package com.flf.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.service.BaseServiceImpl;
import com.flf.entity.ShopIdle;
import com.flf.mapper.ShopIdleMapper;
import com.flf.service.ShopIdleService;

/**
 * 
 * <br>
 * <b>功能：</b>ShopIdleService<br>
 */
@Service("shopIdleService")
public class ShopIdleServiceImpl extends BaseServiceImpl implements ShopIdleService {
	private final static Logger log = Logger.getLogger(ShopIdleServiceImpl.class);

	@Autowired
	private ShopIdleMapper dao;

	@Override
	public ShopIdleMapper getDao() {
		return dao;
	}

	public List<Map<String,String>> listPage(ShopIdle vo) {
		return dao.listPage(vo);
	}

	@Override
	public int saveShopIdle(ShopIdle shopIdle) {
		return dao.saveShopIdle(shopIdle);
	}


	@Override
	public List<Map<String, Object>> getOrderTimeByUserId(Integer userId) {
		return dao.getOrderTimeByUserId(userId);
	}

	@Override
	public int getIdleCount(Integer userId) {
		return dao.getIdleCount(userId);
	}

	@Override
	public void changeStatus(Integer id, String status) {
		dao.changeStatus(id, status);
	}


	
}
