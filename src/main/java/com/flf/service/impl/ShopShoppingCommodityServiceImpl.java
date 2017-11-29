package com.flf.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.base.service.BaseServiceImpl;
import com.flf.mapper.ShopShoppingCommodityMapper;
import com.flf.service.ShopShoppingCommodityService;

/**
 * 
 * <br>
 * <b>功能：</b>ShopShoppingCommodityService<br>
 */
@Service("shopShoppingCommodityService")
public class  ShopShoppingCommodityServiceImpl  extends BaseServiceImpl implements ShopShoppingCommodityService {
  private final static Logger log= Logger.getLogger(ShopShoppingCommodityServiceImpl.class);
	

	@Autowired
    private ShopShoppingCommodityMapper dao;

		
	public ShopShoppingCommodityMapper getDao() {
		return dao;
	}

	

}
