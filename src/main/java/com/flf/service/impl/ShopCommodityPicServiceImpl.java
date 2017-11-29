package com.flf.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.base.service.BaseServiceImpl;
import com.flf.mapper.ShopCommodityPicMapper;
import com.flf.service.ShopCommodityPicService;

/**
 * 
 * <br>
 * <b>功能：</b>ShopCommodityPicService<br>
 */
@Service("shopCommodityPicService")
public class  ShopCommodityPicServiceImpl  extends BaseServiceImpl implements ShopCommodityPicService {
  private final static Logger log= Logger.getLogger(ShopCommodityPicServiceImpl.class);
	

	@Autowired
    private ShopCommodityPicMapper dao;

		
	public ShopCommodityPicMapper getDao() {
		return dao;
	}

	

}
