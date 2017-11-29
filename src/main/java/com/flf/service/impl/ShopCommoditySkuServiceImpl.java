package com.flf.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.criteria.Criteria;
import com.base.dao.BaseDao;
import com.base.service.BaseServiceImpl;
import com.flf.entity.ShopCommodity;
import com.flf.entity.ShopCommoditySku;
import com.flf.entity.ShopOrderDetails;
import com.flf.mapper.ShopCommodityMapper;
import com.flf.mapper.ShopCommoditySkuMapper;
import com.flf.service.ShopCommodityService;
import com.flf.service.ShopCommoditySkuService;

/**
 * 
 * <br>
 * <b>功能：</b>ShopCommodityService<br>
 */
@Service("shopCommoditySkuService")
public class ShopCommoditySkuServiceImpl extends BaseServiceImpl implements ShopCommoditySkuService {
	private final static Logger log = Logger.getLogger(ShopCommoditySkuServiceImpl.class);

	@Autowired
	private ShopCommoditySkuMapper dao;

	@Override
	public ShopCommoditySkuMapper getDao() {
		return dao;
	}

	@Override
	public List<ShopCommoditySku> list(Integer commodityId) {
		return dao.list(commodityId);
	}


}
