package com.flf.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.service.BaseServiceImpl;
import com.flf.entity.ShopCommodityTag;
import com.flf.mapper.ShopCommodityTagMapper;
import com.flf.service.ShopCommodityTagService;

/**
 * 
 * <br>
 * <b>功能：</b>ShopCommodityTagService<br>
 */
@Service("shopCommodityTagService")
public class ShopCommodityTagServiceImpl extends BaseServiceImpl implements ShopCommodityTagService {
	private final static Logger log = Logger.getLogger(ShopCommodityTagServiceImpl.class);

	@Autowired
	private ShopCommodityTagMapper dao;

	@Override
	public ShopCommodityTagMapper getDao() {
		return dao;
	}

	@Override
	public List<Map<String, Object>> listPage(ShopCommodityTag vo) {
		return dao.listPage(vo);
	}

	@Override
	public boolean getShopCommodityTagByName(String tagName) {
		ShopCommodityTag shopCommodityTag = dao.getShopCommodityTagByName(tagName);
		if (null != shopCommodityTag) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<Map<String, Object>> getCommodityTagList() {
		return dao.getCommodityTagList();
	}

}
