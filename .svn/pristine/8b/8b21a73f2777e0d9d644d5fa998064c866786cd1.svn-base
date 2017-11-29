package com.flf.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.criteria.Criteria;
import com.base.service.BaseServiceImpl;
import com.flf.entity.ShopWishList;
import com.flf.mapper.ShopWishListMapper;
import com.flf.service.ShopWishListService;

/**
 * 
 * <br>
 * <b>功能：</b>ShopWishListService<br>
 */
@Service("shopWishListService")
public class ShopWishListServiceImpl extends BaseServiceImpl implements ShopWishListService {
	private final static Logger log = Logger.getLogger(ShopWishListServiceImpl.class);

	@Autowired
	private ShopWishListMapper dao;

	@Override
	public ShopWishListMapper getDao() {
		return dao;
	}

	@Override
	public List<Map<String, Object>> getWishListByUserId(Criteria criteria) {
		return dao.getWishListByUserId(criteria);
	}

	@Override
	public List<Map<String, Object>> listPage(ShopWishList vo) {
		return dao.listPage(vo);
	}

	@Override
	public int updateWishList(Integer userId, Integer commodityId) {
		ShopWishList wl = new ShopWishList();
		wl.setUserId(userId);
		wl.setCommodityNo(commodityId.toString());
		return dao.updateWishList(wl);
	}

}
