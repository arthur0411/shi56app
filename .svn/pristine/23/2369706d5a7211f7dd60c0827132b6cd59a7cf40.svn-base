package com.flf.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.service.BaseServiceImpl;
import com.flf.entity.ShopOrderDetails;
import com.flf.mapper.ShopOrderDetailsMapper;
import com.flf.service.ShopOrderDetailsService;

/**
 * 
 * <br>
 * <b>功能：</b>ShopOrderDetailsService<br>
 */
@Service("shopOrderDetailsService")
public class ShopOrderDetailsServiceImpl extends BaseServiceImpl implements ShopOrderDetailsService {
	private final static Logger log = Logger.getLogger(ShopOrderDetailsServiceImpl.class);

	@Autowired
	private ShopOrderDetailsMapper dao;

	@Override
	public ShopOrderDetailsMapper getDao() {
		return dao;
	}

	@Override
	public List<Map<String, Object>> listAllOrderDetails(int orderId) {
		return dao.listAllOrderDetails(orderId);
	}

	@Override
	public List<ShopOrderDetails> getDetailByOrderId(Integer orderId) {
		return dao.getDetailByOrderId(orderId);
	}

	@Override
	public List<Map<String, Object>> getCommodityrentByOrderId(Integer orderId) {
		return dao.getCommodityrentByOrderId(orderId);
	}

}
