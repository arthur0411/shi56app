package com.flf.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.service.BaseServiceImpl;
import com.flf.entity.ShopVip;
import com.flf.mapper.ShopVipMapper;
import com.flf.service.ShopVipService;

/**
 * 
 * <br>
 * <b>功能：</b>ShopVipService<br>
 */
@Service("shopVipService")
public class ShopVipServiceImpl extends BaseServiceImpl implements ShopVipService {
	private final static Logger log = Logger.getLogger(ShopVipServiceImpl.class);

	@Autowired
	private ShopVipMapper dao;

	@Override
	public ShopVipMapper getDao() {
		return dao;
	}

	@Override
	public List<Map<String, Object>> getShopVipList() {
		return dao.getShopVipList();
	}

	@Override
	public List<ShopVip> listPage(ShopVip vo) {
		return dao.listPage(vo);
	}

	@Override
	public boolean getShopVipByName(String vipName) {
		ShopVip shopVip = dao.getShopVipByName(vipName);
		if (null != shopVip) {
			return true;
		} else {
			return false;
		}
	}

}
