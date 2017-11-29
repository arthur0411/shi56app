package com.flf.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.base.service.BaseServiceImpl;
import com.flf.mapper.ShopStockMapper;
import com.flf.service.ShopStockService;

/**
 * 
 * <br>
 * <b>功能：</b>ShopStockService<br>
 */
@Service("shopStockService")
public class  ShopStockServiceImpl  extends BaseServiceImpl implements ShopStockService {
  private final static Logger log= Logger.getLogger(ShopStockServiceImpl.class);
	

	@Autowired
    private ShopStockMapper dao;

		
	public ShopStockMapper getDao() {
		return dao;
	}


	@Override
	public Integer queryStockById(String id) {
		
		return dao.queryStockById(id);
	}

	

}
