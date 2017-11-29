package com.flf.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.base.service.BaseServiceImpl;
import com.flf.mapper.ShopRecommendMapper;
import com.flf.service.ShopRecommendService;

/**
 * 
 * <br>
 * <b>功能：</b>ShopRecommendService<br>
 */
@Service("shopRecommendService")
public class  ShopRecommendServiceImpl  extends BaseServiceImpl implements ShopRecommendService {
  private final static Logger log= Logger.getLogger(ShopRecommendServiceImpl.class);
	

	@Autowired
    private ShopRecommendMapper dao;

		
	public ShopRecommendMapper getDao() {
		return dao;
	}

	

}
