package com.flf.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.service.BaseServiceImpl;
import com.flf.mapper.ShopPicMapper;
import com.flf.service.ShopPicService;

/**
 * 
 * <br>
 * <b>功能：</b>ShopCommodityBrandService<br>
 */
@Service("shopPicService")
public class ShopPicServiceImpl extends BaseServiceImpl implements ShopPicService {
	private final static Logger log = Logger.getLogger(ShopPicServiceImpl.class);

	@Autowired
	private ShopPicMapper dao;

	@Override
	public ShopPicMapper getDao() {
		return dao;
	}

}
