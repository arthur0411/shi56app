package com.flf.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.service.BaseServiceImpl;
import com.flf.entity.ShopCommodityBrand;
import com.flf.mapper.ShopCommodityBrandMapper;
import com.flf.service.ShopCommodityBrandService;

/**
 * 
 * <br>
 * <b>功能：</b>ShopCommodityBrandService<br>
 */
@Service("shopCommodityBrandService")
public class ShopCommodityBrandServiceImpl extends BaseServiceImpl implements ShopCommodityBrandService {
	private final static Logger log = Logger.getLogger(ShopCommodityBrandServiceImpl.class);

	@Autowired
	private ShopCommodityBrandMapper dao;

	@Override
	public ShopCommodityBrandMapper getDao() {
		return dao;
	}

	@Override
	public List<Map<String, Object>> listPage(ShopCommodityBrand vo) {
		return dao.listPage(vo);
	}

	@Override
	public boolean getShopCommodityBrandByName(String brandName) {
		ShopCommodityBrand shopCommodityBrand = dao.getShopCommodityBrandByName(brandName);
		if (null != shopCommodityBrand) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<Map<String, Object>> getCommodityBrandList() {
		return dao.getCommodityBrandList();
	}

	@Override
	public List<Map<String, Object>> listAll() {
		return dao.listAll();
	}

}
