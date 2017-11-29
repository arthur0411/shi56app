package com.flf.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.service.BaseServiceImpl;
import com.flf.entity.ShopCarousel;
import com.flf.mapper.ShopCarouselMapper;
import com.flf.service.ShopCarouselService;

/**
 * 
 * <br>
 * <b>功能：</b>ShopCarouselService<br>
 */
@Service("shopCarouselService")
public class ShopCarouselServiceImpl extends BaseServiceImpl implements ShopCarouselService {
	private final static Logger log = Logger.getLogger(ShopCarouselServiceImpl.class);

	@Autowired
	private ShopCarouselMapper dao;

	@Override
	public ShopCarouselMapper getDao() {
		return dao;
	}

	@Override
	public List<ShopCarousel> listPage(ShopCarousel vo) {
		return dao.listPage(vo);
	}

}
